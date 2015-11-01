/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invincibagel;

/**
 *
 * @author Chris
 */
public class GamePiece {
 
    
    private static final String FLAVOR_OF_BAGEL = "Pumpernickel";
    private static final String SIZE_OF_BAGEL = "Mini bagel";
    
    public int invinciBagelX = 0;
    public int invinciBagelY = 0;
    public String bagelOrientation = "side";
    public int lifeIndex = 1000;
    public int hitsIndex = 0;
    public String directionFacing = "E";
    public String movementType = "idle";
    public boolean currentlyMoving = false;
    
    GamePiece(){
        invinciBagelX = 0;
        invinciBagelY = 0;
        bagelOrientation = "side";
        lifeIndex = 1000;
        hitsIndex = 0;
        directionFacing = "E";
        movementType = "idle";
        currentlyMoving = false;
    }
    
    GamePiece(int x, int y, String orientation, int lifeSpan, String direction, String movement){
        invinciBagelX = x;
        invinciBagelY = y;
        bagelOrientation = orientation;
        lifeIndex = lifeSpan;
        hitsIndex = 0;
        directionFacing = direction;
        movementType = movement;
        currentlyMoving = false;
    }
    
    public void moveInvinciBagel(int x, int y){
        currentlyMoving = true;
        invinciBagelX = x;
        invinciBagelY = y;
        
    }

    public String getBagelOrientation() {
        return bagelOrientation;
    }

    public void setBagelOrientation(String orientation) {
        bagelOrientation = orientation;
    }

    public int getLifeIndex() {
        return lifeIndex;
    }

    public void setLifeIndex(int lifeSpan) {
        lifeIndex = lifeSpan;
    }

    public int getHitsIndex() {
        return hitsIndex;
    }

    public void setHitsIndex(int damage) {
        hitsIndex = damage;
    }

    public String getDirectionFacing() {
        return directionFacing;
    }

    public void setDirectionFacing(String direction) {
        directionFacing = direction;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movement) {
        movementType = movement;
    }
    
    
}
