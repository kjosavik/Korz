package app.level;

import app.level.implementations.*;

import java.util.AbstractMap;
import java.util.Map;

public class LevelService {
    private static final LevelService INSTANCE = new LevelService();
    private Map<String, Level> levels;

    private LevelService() {}

    private AbstractMap.SimpleEntry<String, Level> create(Level level) {
        return new AbstractMap.SimpleEntry<>(level.getClass().getSimpleName(), level);
    }

    public static LevelService getInstance() {
        return INSTANCE;
    }

    public void loadLevels() {
        levels = Map.ofEntries(
                create(new Bar()),
                create(new Pinball()),
                create(new BathroomDark()),
                create(new Bathroom()),
                create(new Hallway()),
                create(new Kitchen()),
                create(new Death()),
                create(new OfficeNorth()),
                create(new OfficeSouth()),
                create(new Outside()),
                create(new Terminal())
        );
    }

    public Level getStartLevel() {
        return levels.get(BathroomDark.class.getSimpleName());
    }

    public Level getLevel(String type) {
        return levels.get(type);
    }



}
