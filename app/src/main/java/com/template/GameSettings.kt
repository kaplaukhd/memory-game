package com.template

data class CardSettings(
    var cardEmoji: Int,
    var cardIsVisible: Boolean = false,
    var cardIsMatch: Boolean = false,
)

data class Emojies(
    val emoji: Int,
)
class GetEmoji(){
    fun getEmoji(): ArrayList<Emojies> {
        val emoji = arrayListOf<Emojies>()
        emoji.add(Emojies(R.string.emoji_dna))
        emoji.add(Emojies(R.string.emoji_heart))
        emoji.add(Emojies(R.string.emoji_hundred))
        emoji.add(Emojies(R.string.emoji_mountain))
        emoji.add(Emojies(R.string.emoji_ball))
        emoji.add(Emojies(R.string.emoji_game))
        emoji.add(Emojies(R.string.emoji_mellon))
        emoji.add(Emojies(R.string.emoji_space))
        emoji.add(Emojies(R.string.emoji_rainbow))
        emoji.add(Emojies(R.string.emoji_q))
        emoji.add(Emojies(R.string.emoji_w))
        emoji.add(Emojies(R.string.emoji_e))
        emoji.add(Emojies(R.string.emoji_r))
        emoji.add(Emojies(R.string.emoji_t))
        emoji.add(Emojies(R.string.emoji_y))
        emoji.add(Emojies(R.string.emoji_u))
        emoji.add(Emojies(R.string.emoji_i))
        emoji.add(Emojies(R.string.emoji_o))
        emoji.add(Emojies(R.string.emoji_p))
        emoji.add(Emojies(R.string.emoji_a))
        emoji.add(Emojies(R.string.emoji_s))
        emoji.add(Emojies(R.string.emoji_d))
        emoji.add(Emojies(R.string.emoji_f))
        emoji.add(Emojies(R.string.emoji_g))
        emoji.add(Emojies(R.string.emoji_h))
        emoji.add(Emojies(R.string.emoji_j))
        emoji.add(Emojies(R.string.emoji_clover))
        emoji.add(Emojies(R.string.emoji_laptop))
        emoji.add(Emojies(R.string.emoji_aircraft))
        emoji.add(Emojies(R.string.emoji_k))
        emoji.add(Emojies(R.string.emoji_l))
        emoji.add(Emojies(R.string.emoji_z))
        return emoji
    }
}






