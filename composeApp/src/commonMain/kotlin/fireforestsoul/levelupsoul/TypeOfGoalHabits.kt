package fireforestsoul.levelupsoul

enum class TypeOfGoalHabits(val char: Char) {
    NO_MORE('<'),
    AT_LEAST('>'),
}

enum class Old2001000TypeOfGoalHabits() {
    NO_MORE,
    NOT_LITTLE
}

fun toTypeOfGoalHabits(old2001000TypeOfGoalHabits: Old2001000TypeOfGoalHabits): TypeOfGoalHabits {
    return when (old2001000TypeOfGoalHabits) {
        Old2001000TypeOfGoalHabits.NO_MORE -> TypeOfGoalHabits.NO_MORE
        Old2001000TypeOfGoalHabits.NOT_LITTLE -> TypeOfGoalHabits.AT_LEAST
    }
}