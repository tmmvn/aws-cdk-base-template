org.apache.tools.ant.DirectoryScanner.removeDefaultExclude("**/.gitignore")
try {
  val file = File(".gitignore")
  val fr = java.io.FileReader(file)
  val br = java.io.BufferedReader(fr)
  var line = br.readLine()
  while (line != null) {
    val sb = StringBuffer()
    sb.append(line)
    val lineString = sb.toString()
    if (!lineString.contains('#') && lineString.isNotEmpty()) {
      org.apache.tools.ant.DirectoryScanner.addDefaultExclude(lineString)
    }
    line = br.readLine()
  }
  fr.close()
}
catch(e: Exception) {
 println("Error:$e")
}

rootProject.name="cdk-base-app"
include("app")
