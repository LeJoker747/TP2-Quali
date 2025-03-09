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
}


