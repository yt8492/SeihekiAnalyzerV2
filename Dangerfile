# Ignore inline messages which lay outside a diff's range of PR
github.dismiss_out_of_range_messages

# Make it more obvious that a PR is a work in progress and shouldn't be merged yet
warn("PR is classed as Work in Progress") if github.pr_title.include? "[WIP]"

# android lint
Dir.glob("**/lint-results.xml").each { |report|
  android_lint.filtering = true
  android_lint.report_file = report.to_s
  android_lint.lint(inline_mode: true)
}

# ktlint

require 'rexml/document'
Dir.glob("**/ktlint/*.xml").each { |report_file_path|
  report_file = File.new(report_file_path)
  module_path = report_file_path.slice(/(.*)\/build/, 1)
  doc = REXML::Document.new(report_file)
  doc.elements.each("/checkstyle/file") { |element|
    element.attributes["name"] = module_path + "/" + element.attributes["name"]
  }
  File.write(report_file_path.sub(".xml", "_path_changed.xml"), doc)
}

checkstyle_format.base_path = Dir.pwd
Dir.glob("**/ktlint/*_path_changed.xml").each { |report|
  checkstyle_format.report report.to_s
}
