package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    JUMPABLE, //use this status for actors that can utilise JumpAction
    FERTILE, // use this status to check if any grounds near mature is fertile
    INVINCIBLE, // use this status if actor has consumed PowerStar
    DORMANT,
    DORMANTKILL,
    RESETTABLE, // check if object is resettable
    CANTELEPORT,
    FLYING,
    FIRE,
    UNLOCK,
    DRINK,
    CANUSEFIRE,
    FOUNTAIN,
    ENEMY_DRINK
}
