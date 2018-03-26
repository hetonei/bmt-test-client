package bmt.game.managers;


import bmt.game.spells.Spell;
import bmt.game.spells.SpellName;
import bmt.game.spells.necromancer.Cadarkhas;
import bmt.game.spells.necromancer.Nacrayo;
import bmt.models.Player;

public class GameManager {

    public static Spell CastSpell(SpellName sp, Player caster, Player enemy){
        Spell spell = null;
        switch (sp){
            case Cadarkhas:
                spell = new Cadarkhas();
                break;
            case Nacrayo:
                spell = new Nacrayo();
                break;
        }
        assert spell != null;
        spell.SetTargets(caster, enemy);
        return spell;
    }
}
