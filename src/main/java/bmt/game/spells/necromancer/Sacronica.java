package bmt.game.spells.necromancer;

import bmt.game.spells.Effect;
import bmt.game.spells.EffectType;
import bmt.game.spells.Spell;
import bmt.game.spells.SpellName;

import java.util.ArrayList;

public class Sacronica extends Spell {
    public Sacronica (){
        this.Name = SpellName.Sacronica;
        this.Description = "Восполняет 2 единицы здоровья цели и 3 на следующем ходу.";
        this.Priority = 3;
        this.Ultimate = false;
        Effects = new ArrayList<>();
    }

    @Override
    public void PerformEffect(){
        SacronicaHealNow(EnemyCaster);
        SacronicaHealNextStep(EnemyCaster);
        this.ReadyToUse = false;
    }

    private void SacronicaHealNow(boolean EnemyCaster){
        Effect effect = new Effect();
        effect.Priority = 3;
        effect.Type = EffectType.Heal;
        effect.Caster = this.Caster;
        effect.Target = EnemyCaster ? this.Enemy : this.Caster;
        effect.Value = 2;
        effect.LifeSpan = 1;
        Effects.add(effect);
    }

    private void SacronicaHealNextStep(boolean EnemyCaster){
        Effect effect = new Effect();
        effect.UsableInitValue = 1;
        effect.Usable = false;
        effect.Priority = 3;
        effect.Type = EffectType.Heal;
        effect.Caster = this.Caster;
        effect.Target = EnemyCaster ? this.Enemy : this.Caster;
        effect.Value = 3;
        effect.LifeSpan = 2;
        Effects.add(effect);
    }

}
