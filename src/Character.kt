open class Character(
        val level: Int,
        var fire : Int,
        var earth: Int,
        var wind: Int
) {

    open var vitality: Int = level * 2

}