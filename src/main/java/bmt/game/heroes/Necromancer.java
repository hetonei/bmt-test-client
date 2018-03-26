package bmt.game.heroes;

public class Necromancer extends Hero {
    public Necromancer(){
        this.Name = "Necromancer";
        this.Description = "- Когда здоровье некроманта понижается до 5-ти и ниже - некромант наносит дополнительно 2 урона атакующими заклинанями.\n" +
                "- Когда здоровье понижается до 1-го противник получает 4 урона в ход.";
        this.Health = 15;
    }
}
