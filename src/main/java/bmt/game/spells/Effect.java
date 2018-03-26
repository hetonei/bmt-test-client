package bmt.game.spells;

import bmt.models.Player;

import java.util.Comparator;

public class Effect {
    public EffectType Type;
    public Player Caster;
    public Player Target;
    public int Value;
    public int LifeSpan;
    public int UsableInitValue;
    public boolean isUsable(int a) {
        if(LifeSpan==a) return !Usable;
        return Usable;
    }

    public boolean Usable = true;
    public int Priority;
    public int getPriority() {
        return Priority;
    }
    public static Comparator<Effect> PriorityComparator = Comparator.comparingInt(Effect::getPriority);
}
