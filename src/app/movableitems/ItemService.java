package app.movableitems;

import app.Game;
import app.interaction.Command;
import app.interaction.Noun;
import app.interaction.UserFeedback;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class ItemService {
    private static final ItemService INSTANCE = new ItemService();
    private List<Item> items;

    private ItemService() {}

    public static ItemService getInstance() {
        return INSTANCE;
    }

    public void loadItems() {
        items = new ArrayList<>();
        items.add(new CoffeePot());
        items.add(new Computer());
    }

    public void addItem(Item newItem) {
        items.add(newItem);
    }

    public Function<Game, UserFeedback> getItemAction(Command command, String levelName) {
        List<Function<Game, UserFeedback>> functions = items.stream().map(item -> item.getLegalAction(command, levelName)).filter(Objects::nonNull).toList();
        return functions.isEmpty() ? null : functions.get(0);
    }

    public UserFeedback itemDescriptionForLevel (String level) {
        String[] itemDescriptions = items.stream()
                .filter(item -> item.getPosition().equals(level) && item.hasBeenMoved())
                .map(item -> "There is a " + item.getName() + " on the floor.")
                .toArray(String[]::new);
        if (itemDescriptions.length == 0 ) {
            return UserFeedback.of("");
        }
        return UserFeedback.of(itemDescriptions);
    }

    public boolean isItemInSpawn(Noun noun) {
        return items.stream().anyMatch(item -> item.getItemNoun().equals(noun) && !item.hasBeenMoved());
    }

    public boolean isItemOnPlayer(Noun noun) {
        return items.stream().anyMatch(item -> item.getItemNoun().equals(noun) && item.getPosition().equals("player"));
    }

    public String itemsOnPlayer() {
        List<String> itemNames = items.stream().filter(item -> item.getPosition().equals("player")).map(Item::getName).toList();
        if (itemNames.isEmpty()) {
            return "Your inventory is empty";
        }
        return "The following items are in your inventory: " + String.join(" , ", itemNames);
    }
}
