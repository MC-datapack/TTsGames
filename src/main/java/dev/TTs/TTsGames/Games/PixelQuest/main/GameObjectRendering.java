package dev.TTs.TTsGames.Games.PixelQuest.main;

import dev.TTs.TTsGames.Games.PixelQuest.entities.GameObject;

import java.util.List;

public interface GameObjectRendering {
    void addGameObject(GameObject gameObject);
    void addMultipleGameObjects(GameObject[] gameObjects);
    void addMultipleGameObjects(List<GameObject> gameObjects);
    void removeGameObject(GameObject gameObject);
}
