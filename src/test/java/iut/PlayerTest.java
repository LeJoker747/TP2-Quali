package iut;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    public void ShouldCreatePlayer() {
        // GIVEN
        Player hunter = new Player();
        // WHEN
        int initialHealthPoints = hunter.health();
        // THEN
        assertThat(initialHealthPoints).isEqualTo(100);
    }

    @Test
    public void ShouldBeAlive(){
        // GIVEN
        Player hunter = new Player();
        // WHEN
        boolean isAlive = hunter.isAlive();
        // THEN
        assertThat(isAlive).isEqualTo(true);
    }

    @Test
    public void ShouldBeDead(){
        // GIVEN
        Player hunter = new Player();
        Player prey = new Player();
        // WHEN
        hunter.move(-hunter.getPlace());
        hunter.move(prey.getPlace());
        for (int i = 0; i < 10; i++){
            hunter.hit(prey);
        }
        boolean isAlive = prey.isAlive();
        // THEN
        assertThat(isAlive).isEqualTo(false);
    }

    @Test
    public void ShouldReceiveDamage(){
        // GIVEN
        Player hunter = new Player();
        Player prey = new Player();
        int numberOfHit = (int) (Math.random()*11);       
        // WHEN
        hunter.move(-hunter.getPlace());
        hunter.move(prey.getPlace()); 
        for (int i = 0; i < numberOfHit; i++){
            hunter.hit(prey);
        }
        int healthPoints = prey.health();
        // THEN
        assertThat(healthPoints).isEqualTo(100-10*numberOfHit);
    }

    @Test
    public void ShouldNotHeal(){
        // GIVEN
        Player hunter = new Player();
        Player prey = new Player();
        // WHEN
        hunter.move(-hunter.getPlace());
        hunter.move(prey.getPlace()); 
        for (int i = 0; i < 10; i++){
            hunter.hit(prey);
        }
        prey.heal();
        int healthPoints = prey.health();
        // THEN
        assertThat(healthPoints).isEqualTo(0);
    }

    @Test
    public void ShouldHeal(){
        // GIVEN
        Player hunter = new Player();
        Player prey = new Player();
        int numberOfHit = (int) (Math.random()*10);
        // WHEN
        hunter.move(-hunter.getPlace());
        hunter.move(prey.getPlace()); 
        for (int i = 0; i < numberOfHit; i++){
            hunter.hit(prey);
        }
        prey.heal();
        int healthPoints = prey.health();
        // THEN
        assertThat(healthPoints).isEqualTo(100-numberOfHit*10+10);
    }

    @Test
    public void ShouldNotBeInAFaction(){
        // GIVEN
        Player hunter = new Player();
        // WHEN
        Faction faction = hunter.getFaction();
        // THEN
        assertThat(faction).isEqualTo(null);
    }

    @Test
    public void ShouldBeInAFaction(){
        // GIVEN
        Player hunter = new Player();
        Faction factionExpected = new Faction("faction1");
        // WHEN
        hunter.joinFaction(factionExpected);
        Faction faction = hunter.getFaction();
        // THEN
        assertThat(faction).isEqualTo(factionExpected);
    }

    @Test
    public void ShouldNotChangeFaction(){
        // GIVEN
        Player hunter = new Player();
        Faction factionExpected = new Faction("faction1");
        Faction factionNotExpected = new Faction("faction2");
        // WHEN
        hunter.joinFaction(factionExpected);
        hunter.joinFaction(factionNotExpected);
        Faction faction = hunter.getFaction();
        // THEN
        assertThat(faction).isEqualTo(factionExpected);
    }

    @Test
    public void ShouldChangeFaction(){
        // GIVEN
        Player hunter = new Player();
        Faction factionNotExpected = new Faction("faction1");
        Faction factionExpected = new Faction("faction2");
        // WHEN
        hunter.joinFaction(factionNotExpected);
        hunter.leaveFaction();
        hunter.joinFaction(factionExpected);
        Faction faction = hunter.getFaction();
        // THEN
        assertThat(faction).isEqualTo(factionExpected);
    }

    @Test
    public void ShouldBeAlly(){
        // GIVEN
        Player hunter = new Player();
        Player prey = new Player();
        Faction faction = new Faction("faction1");
        // WHEN 
        hunter.joinFaction(faction);
        prey.joinFaction(faction);
        boolean isAlly = hunter.isAlly(prey);
        // THEN
        assertThat(isAlly).isEqualTo(true);
    }

    @Test
    public void ShouldNotBeAlly(){
        // GIVEN
        Player hunter = new Player();
        Player prey = new Player();
        Faction faction = new Faction("faction1");
        Faction faction2 = new Faction("faction2");
        // WHEN 
        hunter.joinFaction(faction);
        prey.joinFaction(faction2);
        boolean isAlly = hunter.isAlly(prey);
        // THEN
        assertThat(isAlly).isEqualTo(false);
    }

    @Test
    public void ShouldAttack(){
        // GIVEN
        Player hunter = new Player();
        Player prey = new Player();
        Faction faction = new Faction("faction1");
        Faction faction2 = new Faction("faction2");
        // WHEN 
        hunter.move(-hunter.getPlace());
        hunter.move(prey.getPlace()); 
        hunter.joinFaction(faction);
        prey.joinFaction(faction2);
        hunter.hit(prey);
        int healthPoints = prey.health();
        // THEN
        assertThat(healthPoints).isEqualTo(90);
    }

    @Test
    public void ShouldNotAttack(){
        // GIVEN
        Player hunter = new Player();
        Player prey = new Player();
        Faction faction = new Faction("faction1");
        // WHEN 
        hunter.move(-hunter.getPlace());
        hunter.move(prey.getPlace()); 
        hunter.joinFaction(faction);
        prey.joinFaction(faction);
        hunter.hit(prey);
        int healthPoints = prey.health();
        // THEN
        assertThat(healthPoints).isEqualTo(100);
    }

    @Test
    public void ShouldHealAlly(){
        // GIVEN
        Player hunter = new Player();
        Player prey = new Player();
        Player damagePlayer = new Player();
        Faction faction = new Faction("faction1");
        // WHEN 
        damagePlayer.move(-damagePlayer.getPlace());
        damagePlayer.move(prey.getPlace()); 
        hunter.joinFaction(faction);
        prey.joinFaction(faction);
        damagePlayer.hit(prey);
        damagePlayer.hit(prey);
        hunter.heal(prey);
        int healthPoints = prey.health();
        // THEN
        assertThat(healthPoints).isEqualTo(90);
    }

    @Test
    public void ShouldBeInRangeMelee(){
        // GIVEN
        Player hunter = new Player();
        Player prey = new Player();       
        // WHEN
        hunter.move(-hunter.getPlace());
        hunter.move(prey.getPlace()); 
        hunter.hit(prey);
        int healthPoints = prey.health();
        // THEN
        assertThat(healthPoints).isEqualTo(90);
    }

    @Test
    public void ShouldNotBeInRangeMelee(){
        // GIVEN
        Player hunter = new Player();
        Player prey = new Player();       
        // WHEN
        hunter.move(50);
        hunter.hit(prey);
        int healthPoints = prey.health();
        // THEN
        assertThat(healthPoints).isEqualTo(100);
    }

    @Test
    public void ShouldBeInRangeRanged(){
        // GIVEN
        Player hunter = new Player();
        Player prey = new Player();       
        // WHEN
        hunter.setFighterType(FighterType.RANGED);
        hunter.hit(prey);
        int healthPoints = prey.health();
        // THEN
        assertThat(healthPoints).isEqualTo(90);
    }

    @Test
    public void ShouldNotBeInRangeRanged(){
        // GIVEN
        Player hunter = new Player();
        Player prey = new Player();       
        // WHEN
        hunter.setFighterType(FighterType.RANGED);
        hunter.move(50);
        hunter.hit(prey);
        int healthPoints = prey.health();
        // THEN
        assertThat(healthPoints).isEqualTo(100);
    }
}


