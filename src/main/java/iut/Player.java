package iut;

class Player {
    private int health;

    Player(){
        this.health = 100;
    }

    public int health(){return this.health;}

    public boolean isAlive(){
        if (this.health <= 0){
            return false;
        }
        return true;   
    }

    public void hit(Player opponent){
        if (opponent.isAlive()){
            opponent.health -= 10;
        }
    }

    public void heal(){
        if (this.isAlive()){
            this.health += 10;
        }
    }
}

