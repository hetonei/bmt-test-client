package bmt.game.spells.necromancer;


import bmt.game.spells.Effect;
import bmt.game.spells.EffectType;
import bmt.game.spells.Spell;
import bmt.game.spells.SpellName;

import java.util.ArrayList;

public class Cadarkhas extends Spell {
    public Cadarkhas (){
        this.Name = SpellName.Cadarkhas;
        this.Description = "Наносит вам 2 урона.\n" +
        "Наносит противнику 2 урона каждый ход на протяжении\n" +
        "3-х ходов, начиная с этого.";
        this.Priority = 4;
        this.Ultimate = false;
        Effects = new ArrayList<>();
    }
    @Override
    public void PerformEffect(){
        CadarkhasDamageSelf();
        CadarkhasDamageEnemy();
        this.ReadyToUse = false;
    }

    private void CadarkhasDamageSelf(){
        Effect effect = new Effect();
        effect.Priority = 4;
        effect.Type = EffectType.Damage;
        effect.Caster = this.Caster;
        effect.Target = this.Caster;
        effect.Value = 2;
        effect.LifeSpan = 1;
        Effects.add(effect);
    }

    private void CadarkhasDamageEnemy(){
        Effect effect = new Effect();
        effect.Priority = 5;
        effect.Type = EffectType.Damage;
        effect.Caster = this.Caster;
        effect.Target = this.Enemy;
        effect.Value = 3;
        effect.LifeSpan = 3;
        Effects.add(effect);
    }
}
