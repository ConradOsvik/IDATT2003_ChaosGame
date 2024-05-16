package edu.ntnu.stud.controllers;

import edu.ntnu.stud.views.home.HomeView;

public class HomeController extends Controller {
  private final HomeView view;

  public HomeController() {
    this.view = new HomeView(this);
  }

  public HomeView getView() {
    return view;
  }
}
