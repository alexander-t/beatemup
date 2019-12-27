package se.tarlinder.beatemup;

import se.tarlinder.ge2d.Component;
import se.tarlinder.ge2d.Entity;
import se.tarlinder.ge2d.Time;
import se.tarlinder.ge2d.input.Input;
import se.tarlinder.ge2d.animation.AnimationController;

public class PlayerMovement extends Component {

    private final int speed = 100;
    private boolean moving = false;
    private Entity player;
    private AnimationController animationController;

    @Override
    public void start() {
        player = getOwner();
        animationController = (AnimationController) player.getComponent(AnimationController.class);
        player.x = 120;
        player.y = 100;
    }

    @Override
    public void update() {
        boolean movingNow = false;
        if (Input.dPressed) {
            player.x += Math.ceil(speed * Time.deltaTime());
            movingNow = true;
        }
        if (Input.aPressed) {
            player.x -= Math.ceil(speed * Time.deltaTime());
            movingNow = true;
        }

        // Update animation of there's been a change in movement
        if (movingNow != moving) {
            moving = movingNow;
            animationController.setIdle(!moving);
        }
    }
}
