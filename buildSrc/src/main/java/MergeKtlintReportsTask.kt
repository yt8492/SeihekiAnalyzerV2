import org.dom4j.DocumentHelper
import org.dom4j.io.OutputFormat
import org.dom4j.io.SAXReader
import org.dom4j.io.XMLWriter
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.io.FileWriter

open class MergeKtlintReportsTask : DefaultTask() {

    @TaskAction
    fun merge() {
        val rootDir = project.rootDir
        val reports = getReportFileRecursive(rootDir)
        val outputDir = File("$rootDir/report")
        if (!outputDir.exists()) {
            outputDir.mkdir()
        }
        val xmlReader = SAXReader()
        val document = reports.firstOrNull()?.let { xmlReader.read(it) } ?: return
        val root = document.rootElement.createCopy()
        reports.drop(1).forEach { report ->
            val nodes = xmlReader.read(report).document.rootElement.elements()
            nodes.forEach {
                root.add(it.createCopy())
            }
        }
        val outputDocument = DocumentHelper.createDocument()
        outputDocument.add(root)
        FileWriter("$outputDir/ktlint.xml").use {
            val format = OutputFormat.createPrettyPrint()
            val writer = XMLWriter(it, format)
            writer.write(root)
        }
    }
}

private fun getReportFileRecursive(directory: File): List<File> {
    val files = directory.listFiles()?.filterNot {
        it.name.startsWith(".") && it.isFile
    } ?: emptyList()
    val reportsDir = files.find { it.name == "reports" }
    return if (reportsDir != null) {
        val ktlintDir = File("$reportsDir/ktlint")
        if (ktlintDir.exists()) {
            ktlintDir.listFiles { _, name ->
                name.endsWith(".xml")
            }?.asList() ?: emptyList()
        } else {
            emptyList()
        }
    } else {
        files.flatMap { getReportFileRecursive(it) }
    }
}
