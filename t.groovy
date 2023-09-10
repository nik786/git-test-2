//def fileContent = sh(script: "cat s2.txt", returnStdout: true)

def file = new File("s2.txt")
def fileContent = []

if (file.exists()) {
    fileContent = file.text.split('\n')
} else {
    println "File 's2.txt' not found."
    return
}

println fileContent

// Use regular expressions to extract 'score' values
def pattern = /score="(\d+)"/
def matcher = (fileContent.join('\n') =~ pattern)

// Initialize a list to store the extracted 'score' values
def scores = matcher.collect { it[1] }

// Check if there are at least two 'score' values
if (scores.size() >= 2) {
    // Get the second 'score' value
    def s = scores[1] as Integer

    // Check the 'score' value and exit accordingly
    if (s >= 99) {
        println "$s -> correct"
    } else {
        println "$s -> incorrect"
        return
    }
} else {
    println "Insufficient 'score' values found in the file."
    return
}

