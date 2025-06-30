package fireforestsoul.levelupsoul

enum class TypeOfGoalHabits(val char: Char) {
    NO_MORE('<'),
    AT_LEAST('>'),
}

enum class Old3000000TypeOfGoalHabits() {
    NO_MORE,
    NOT_LITTLE
}

fun toTypeOfGoalHabits(old3000000TypeOfGoalHabits: Old3000000TypeOfGoalHabits): TypeOfGoalHabits {
    return when (old3000000TypeOfGoalHabits) {
        Old3000000TypeOfGoalHabits.NO_MORE -> TypeOfGoalHabits.NO_MORE
        Old3000000TypeOfGoalHabits.NOT_LITTLE -> TypeOfGoalHabits.AT_LEAST
    }
}