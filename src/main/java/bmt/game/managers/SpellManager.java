package bmt.game.managers;

import bmt.game.spells.Effect;
import bmt.game.spells.Spell;

import java.util.ArrayList;
import java.util.List;

public class SpellManager {

    public static List<Effect> CastSpells(List<Spell> spells){
        List<Effect> effects = new ArrayList<>();
        spells.sort(Spell.PriorityComparator);
        spells.forEach(sp -> {
            switch (sp.Name){
                case Avendos:
                    effects.clear();
                    break;
                default:
                    sp.PerformEffect();
                    effects.addAll(sp.Effects);
                    break;
            }
        });
        return effects;
    }

    public static List<Effect> UseEffects(List<Effect> effects){
        effects.sort(Effect.PriorityComparator);

        effects.stream()
                .filter(e -> e.isUsable(e.UsableInitValue))
                .forEach(SpellManager::UseEffect);

        effects.forEach(e -> e.LifeSpan-=1);
        List<Effect> FilteredEffects = new ArrayList<>();
        effects.stream().filter(e -> e.LifeSpan != 0).forEach(FilteredEffects::add);

        return FilteredEffects;
    }

    private static void UseEffect(Effect ef){
        switch (ef.Type){
            case Heal:
                ef.Target.setHealth(ef.Target.getHealth()+ef.Value);
                break;
            case Damage:
                ef.Target.setHealth(ef.Target.getHealth()-ef.Value);
                //+ player bonus damage
                break;
            case BonusDamage:

                break;
        }
    }
}
