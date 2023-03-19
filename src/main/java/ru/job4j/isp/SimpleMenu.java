package ru.job4j.isp;

import java.util.*;

public class SimpleMenu implements Menu {

        private final List<MenuItem> rootElements = new ArrayList<>();

        @Override
        public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
            boolean rsl = false;
            Optional<ItemInfo> info = findItem(parentName);
            if (info.isEmpty()) {
                rsl = rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            } else {
                MenuItem menuItem = info.get().menuItem;
                menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
                rsl = true;
            }
            return rsl;
        }

        @Override
        public Optional<MenuItemInfo> select(String itemName) {
           return findItem(itemName).map(s -> new MenuItemInfo(s.menuItem, s.number));
        }

        @Override
        public Iterator<MenuItemInfo> iterator() {
            DFSIterator iterator = new DFSIterator();
            return new Iterator() {
                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public MenuItemInfo next() {
                    ItemInfo info = iterator.next();
                    return new MenuItemInfo(info.menuItem, info.number);
                }
            };
        }

        private Optional<ItemInfo> findItem(String name) {
            Optional<ItemInfo> rsl = Optional.empty();
            DFSIterator iterator = new DFSIterator();
            while (iterator.hasNext()) {
                ItemInfo info = iterator.next();
                if (info.menuItem.getName().equals(name)) {
                    rsl = Optional.of(info);
                    break;
                }
            }
            return rsl;
        }

        private static class SimpleMenuItem implements MenuItem {

            private String name;
            private List<MenuItem> children = new ArrayList<>();
            private ActionDelegate actionDelegate;

            public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
                this.name = name;
                this.actionDelegate = actionDelegate;
            }

            public SimpleMenuItem(String name, List<MenuItem> children,
                                  ActionDelegate actionDelegate) {
                this.name = name;
                this.children = children;
                this.actionDelegate = actionDelegate;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public List<MenuItem> getChildren() {
                return children;
            }

            @Override
            public ActionDelegate getActionDelegate() {
                return actionDelegate;
            }
        }

        private class DFSIterator implements Iterator<ItemInfo> {

            private Deque<MenuItem> stack = new LinkedList<>();

            private Deque<String> numbers = new LinkedList<>();

            DFSIterator() {
                int number = 1;
                for (MenuItem item : rootElements) {
                    stack.addLast(item);
                    numbers.addLast(String.valueOf(number++).concat("."));
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public ItemInfo next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                MenuItem current = stack.removeFirst();
                String lastNumber = numbers.removeFirst();
                List<MenuItem> children = current.getChildren();
                int currentNumber = children.size();
                for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                    stack.addFirst(i.previous());
                    numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--))
                            .concat("."));
                }
                return new ItemInfo(current, lastNumber);
            }

        }

        private class ItemInfo {
            private MenuItem menuItem;
            private String number;

            public ItemInfo(MenuItem menuItem, String number) {
                this.menuItem = menuItem;
                this.number = number;
            }
        }
}
