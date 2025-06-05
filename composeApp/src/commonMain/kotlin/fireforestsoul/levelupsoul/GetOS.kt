package fireforestsoul.levelupsoul

fun isDesktop(): Boolean {
    val os = System.getProperty("os.name").lowercase()
    return os.contains("win") || os.contains("mac") || os.contains("linux")
}