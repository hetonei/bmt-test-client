package bmt.game.spells;


import bmt.models.Player;

import java.util.Comparator;
import java.util.List;

public class Spell{

    public SpellName Name;
    public String Description;

    public int Priority;
    public boolean Ultimate;
    public boolean ReadyToUse = true;
    public List<Effect> Effects;
    public Player Caster;
    public Player Enemy;
    public boolean EnemyCaster;

    public void SetTargets(Player caster, Player enemy){
        this.Caster = caster;
        this.Enemy = enemy;
    }
    public void PerformEffect(){};

    public int getPriority() {
        return Priority;
    }
    public static Comparator<Spell> PriorityComparator = Comparator.comparingInt(Spell::getPriority);
}
