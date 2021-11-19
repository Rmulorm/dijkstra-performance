package com.shortestpath.priorityqueue;

public interface PriorityQueue<E extends PriorityObject> {
  void add(E item);

  void decreasePriority(E item, double priority);

  E extractMin();

  void clear();

  int size();
}
