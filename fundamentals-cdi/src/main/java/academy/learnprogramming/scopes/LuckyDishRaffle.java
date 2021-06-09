package academy.learnprogramming.scopes;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LuckyDishRaffle {

    @Inject
    private List<String> luckyDishes;

    private final Random random = new Random();

    public String getYourLuckyDish() {
	return luckyDishes.get(random.nextInt(8));
    }

}
