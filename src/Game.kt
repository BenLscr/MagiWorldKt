import java.util.*

private val sc = Scanner(System.`in`)

private lateinit var playerOne: Character
private lateinit var playerTwo: Character

fun main() {
    println("Welcome to MagiWorldKt !")
    startThePlayerSelection()
}

private fun startThePlayerSelection() {
    var player = 0
    do {
        player++
        val classSelected: Int = selectClass(player)
        val levelSelected: Int = selectLevel(player)
        val fireSet: Int = setFire(player, levelSelected)
        val earthSet: Int = setEarth(player, levelSelected, fireSet)
        val windSet: Int = setWind(player, levelSelected, fireSet, earthSet)
        buildTheCharacter(player, classSelected, levelSelected, fireSet, earthSet, windSet)
        overviewOfTheCharacterCreated(player)
    } while (player != 2)
}

private fun selectClass(whoIs: Int): Int {
    println("Player $whoIs choose your character :")
    println("1 - Mage")
    println("2 - Berserk")
    println("3 - Thief")
    var classSelected: Int = 0
    do {
        when (sc.nextInt()) {
            1 -> classSelected = 1
            2 -> classSelected = 2
            3 -> classSelected = 3
            else -> println("Please, enter only a correct number to choose your class.")
        }
    } while (classSelected == 0)
    return classSelected
}

private fun selectLevel(whoIs: Int): Int {
    println("Player $whoIs, enter the level of your character : ")
    println("(min : 1, max 200)")
    return sc.nextInt()
}

private fun setFire(whoIs: Int, levelSelected: Int): Int {
    println("Player $whoIs, you have $levelSelected characteristic points to set. How many do you want set in fire ?")
    val input = sc.nextInt()
    var fireSet: Int = -1
    do {
        if (input >= 0 || input <= levelSelected) {
            fireSet = input
        } else {
            println("Please retry and set a correct value. $levelSelected characteristic points to set. How many do you want set in fire ?")
        }
    } while (fireSet == -1)
    return fireSet
}

private fun setEarth(whoIs: Int, levelSelected: Int, fireSet: Int): Int {
    val balance: Int = levelSelected - fireSet
    println("Player $whoIs, you have $balance characteristic points to set. How many do you want set in earth ?")
    val input = sc.nextInt()
    var earthSet: Int = -1
    do {
        if (input >= 0 || input <= balance) {
            earthSet = input
        } else {
            println("Please retry and set a correct value. $balance characteristic points to set. How many do you want set in earth ?")
        }
    } while (earthSet == -1)
    return earthSet
}

private fun setWind(whoIs: Int, levelSelected: Int, fireSet: Int, earthSet: Int): Int {
    val balance: Int = levelSelected - fireSet - earthSet
    println("Player $whoIs, you have $balance characteristic points to set. How many do you want set in wind ?")
    val input = sc.nextInt()
    var windSet: Int = -1
    do {
        if (input >= 0 || input <= balance) {
            windSet = input
        } else {
            println("Please retry and set a correct value. $balance characteristic points to set. How many do you want set in wind ?")
        }
    } while (windSet == -1)
    return windSet
}

private fun buildTheCharacter(player: Int, classSelected: Int, levelSelected: Int, fireSet: Int, earthSet: Int, windSet: Int) {
    var character: Character? = null
    when (classSelected) {
        1 -> character = Mage(levelSelected, fireSet, earthSet, windSet)
        2 -> character = Berserk(levelSelected, fireSet, earthSet, windSet)
        3 -> character = Thief(levelSelected, fireSet, earthSet, windSet)
    }
    character?.let {
        when (player) {
            1 -> playerOne = character
            2 -> playerTwo = character
        }
    }
}

private fun overviewOfTheCharacterCreated(player: Int) {
    println("Overview of your character, player $player :")
    if (player == 1) {
        println("Class : ${playerOne.javaClass}")
        println("Level : ${playerOne.level}")
        println("Vitality : ${playerOne.vitality}")
        println("Fire : ${playerOne.fire}")
        println("Earth : ${playerOne.earth}")
        println("Wind : ${playerOne.wind}")
    } else if (player == 2) {
        println("Class : ${playerTwo.javaClass}")
        println("Level : ${playerTwo.level}")
        println("Vitality : ${playerTwo.vitality}")
        println("Fire : ${playerTwo.fire}")
        println("Earth : ${playerTwo.earth}")
        println("Wind : ${playerTwo.wind}")
    }
}