package p.squidgames.games;


public interface GameActions {
    void onSpawn();
    void onStart();
    void onEnd();
    void onTimeUp();
    void onSkip();

    void onPlayerDeath();
}
