package com.iafenvoy.annoyingvillagers.util;

@SuppressWarnings("unused")
public enum Episode {
    None("None", Type.None),
    Unknown("Unknown", Type.None),
    TheStruggle("The Struggle", Type.FracturesSeason1),
    ColdAsIce("Cold As Ice", Type.FracturesSeason1),
    BeginAgain("Begin Again", Type.Special),
    WeAreTheDanger("We Are The Danger", Type.FracturesSeason1),
    JustSoYouKnow("Just So You Know", Type.FracturesSeason1),
    Goodbye("Goodbye", Type.FracturesSeason1),
    WingsOfSalvation("Wings Of Salvation", Type.FracturesSeason2),
    HardPillToSwallow("Hard Pill To Swallow", Type.FracturesSeason2),
    WitherHeart("Wither Heart", Type.FracturesSeason2),
    EnderWish("Ender Wish", Type.FracturesSeason2),
    WishingDead("Wishing Dead", Type.FracturesSeason2),
    Falling("Falling", Type.FracturesSeason2),
    ColdAsIceRemake("Cold As Ice Remake", Type.FracturesSeason2),
    Nightmares("Nightmares", Type.Special),
    Poison("Poison", Type.Special),
    BeAfraid("Be Afraid", Type.FracturesSeason3),
    Eternal("Eternal", Type.FracturesSeason3),
    GottaGetOuttaHere("Gotta Get Outta Here", Type.FracturesSeason3),
    ClearSkies("Clear Skies", Type.FracturesSeason3),
    ToTheVoid("To The Void", Type.Special),
    BackIntoDarkness("Back Into Darkness", Type.FracturesSeason3),
    AlreadyDead("Already Dead", Type.FracturesSeason3),
    PreachToTheChoir("Preach To The Choir", Type.FracturesSeason3),
    WeAreTheDangerXL("We Are The Danger XL", Type.FracturesSeason3),
    TheBrave("The Brave", Type.EnderKingdom),
    ITryToday("I Try Today", Type.FracturesSeason4);
    private final String name;
    private final Type type;

    Episode(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    enum Type {
        None, FracturesSeason1, FracturesSeason2, FracturesSeason3, FracturesSeason4, Special, EnderKingdom
    }
}
