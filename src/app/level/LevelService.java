package app.level;

import java.util.AbstractMap;
import java.util.Map;

public class LevelService {
    private static final LevelService instance = new LevelService();
    private final Map<String, Level> levels;


    private LevelService(){
        levels = Map.ofEntries(create(new Bar()), create(new Pinball())
        );
    }

    private AbstractMap.SimpleEntry<String, Level> create(Level level) {
        return new AbstractMap.SimpleEntry<>(level.getClass().getSimpleName(), level);
    }

    public static LevelService getInstance() {
        return instance;
    }

    public Level getStartLevel() {
        return levels.get(Pinball.class.getSimpleName());
    }

    public Level getLevel(String type) {
        return levels.get(type);
    }
}
