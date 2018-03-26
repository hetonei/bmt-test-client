package bmt.models;

import bmt.game.heroes.Hero;
import bmt.game.spells.SpellName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private String id;
    private String username;
    Map<SpellName, Boolean> usableSpells;
    private String heroname;
    private int health;
    public Player(Hero hero) {
        heroname = hero.Name;
        health = hero.Health;
        usableSpells = new HashMap<>();
    }
}
