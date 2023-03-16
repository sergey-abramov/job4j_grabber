package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.List;

public interface Store {

   void add(Food food);

   List<Food> getFoods();

   default int distribution(Food food) {
      int ex = food.getExpiryDate().getDayOfYear();
      int now = LocalDate.now().getDayOfYear();
      int create = food.getCreateDate().getDayOfYear();
      int i = now - create;
      int d = ex - create;
      double r = (double) i / d;
      return (int) (r * 100);
   }
}
