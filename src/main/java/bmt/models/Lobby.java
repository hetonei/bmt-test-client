package bmt.models;

import bmt.game.spells.Effect;
import bmt.game.spells.Spell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lobby {
    private String id;
    private Player player1;
    private Player player2;
    private int turnnumber = 0;
    private int spellcounter = 0;
    private List<Spell> usedspells;
    private List<Effect> effects;
}
