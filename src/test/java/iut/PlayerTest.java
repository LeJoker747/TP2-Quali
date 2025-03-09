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
        int numberOfHit = (int) Math.random()*11;
        // WHEN
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
        int numberOfHit = (int) Math.random()*10;
        // WHEN
        for (int i = 0; i < numberOfHit; i++){
            hunter.hit(prey);
        }
        prey.heal();
        int healthPoints = prey.health();
        // THEN
        assertThat(healthPoints).isEqualTo(100-numberOfHit*10+10);
    }
}


