fun main() {
    val coffeeMachine = CoffeeMachine()
    while(true) {
        println(" ")
        println("Write action (buy, fill, take, remaining, exit): ")
        print("> ")
        val action = readln()
        coffeeMachine.action(action)
        if (action == "buy") {
            println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back: \n")
            val act = readln()
            coffeeMachine.buy(act)
        }
        if (coffeeMachine.exit) break
    }
}


class CoffeeMachine(
    private var water: Int = 400,
    private var milk: Int = 540,
    private var beans: Int = 120,
    private var disCup: Int = 9,
    private var dollars: Int = 550
) {


    private fun take() {
        println("I gave you \$550")
        dollars = 0
    }

    private fun layout(){
        println("The coffee machine has:\n" +
                "$water ml of water\n" +
                "$milk ml of milk\n" +
                "$beans g of coffee beans\n" +
                "$disCup disposable cups\n" +
                "\$$dollars of money\n")
    }

    fun buy(action: String) {
        var waterForBuy = 0
        var milkForBuy = 0
        var beansForBuy = 0
        var price = 0
        when(action) {
            "1" -> {
                waterForBuy = 250
                milkForBuy = 0
                beansForBuy = 16
                price = 4
            }
            "2" -> {
                waterForBuy = 350
                milkForBuy = 75
                beansForBuy = 20
                price = 7
            }
            "3" -> {
                waterForBuy = 200
                milkForBuy = 100
                beansForBuy = 12
                price = 6
            }
            "back" -> return
            "else" -> println("Wrong input!")
        }
        val res = mutableListOf(
            water - waterForBuy < 0,
            milk - milkForBuy< 0,
            beans - beansForBuy< 0,
            disCup - 1 < 0
        )
        val name = mutableListOf(
            "water", "beans", "milk", "cup"
        )
        for (index in res.indices) {
            if (res[index]) {
                println("Sorry, not enough ${name[index]}!")
                break
            } else {
                println("I have enough resources, making you a coffee!")
                water -= waterForBuy
                milk -= milkForBuy
                beans -= beansForBuy
                disCup -= 1
                dollars += price
                break
            }
        }
    }

    private fun fill() {
        try {
            println("Write how many ml of water you want to add: ")
            print("> ")
            val addWater = readln().toInt()
            if (addWater < 0) throw Exception("Wrong input!")
            water += addWater
            println("Write how many ml of milk you want to add: ")
            print("> ")
            val addMilk = readln().toInt()
            if (addMilk < 0) throw Exception("Wrong input!")
            milk += addMilk
            println("Write how many ml of beans you want to add: ")
            print("> ")
            val addBeans = readln().toInt()
            if (addBeans < 0) throw Exception("Wrong input!")
            beans += addBeans
            println("Write how many ml of cups you want to add: ")
            print("> ")
            val addCups = readln().toInt()
            if (addCups < 0) throw Exception("Wrong input!")
            disCup += addCups
        }
        catch (e: Exception) {
            println(e.message)
            return
        }

    }

    var exit = false

    fun action(action: String) {
        when (action) {
            "fill" -> fill()
            "take" -> take()
            "remaining" -> layout()
            "exit" -> exit = true
            else -> println("Wrong input!")
        }
    }
}







