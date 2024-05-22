package edu.ntnu.stud.enums;

/**
 * The Event enum represents various events that can occur in the application. These events can be
 * used to trigger specific actions or behaviors.
 *
 * <p>Here are the events that this enum can represent:
 *
 * <ul>
 *   <li>DARK_MODE_TOGGLED: Represents the event of toggling the dark mode.
 *   <li>CHAOS_GAME_DESCRIPTION_UPDATED: Represents the event of updating the description of the
 *       chaos game.
 *   <li>CANVAS_SIZE_UPDATED: Represents the event of updating the size of the canvas.
 *   <li>SET_PRESET: Represents the event of setting a preset.
 *   <li>RUN_CHAOS_GAME: Represents the event of running the chaos game.
 *   <li>STEPS_RAN: Represents the event of running steps.
 *   <li>LOAD_FILE: Represents the event of loading a file.
 *   <li>SAVE_FILE: Represents the event of saving a file.
 *   <li>OPEN_EDIT_DESCRIPTION_DIALOG: Represents the event of opening the edit description dialog.
 *   <li>UPDATE_DESCRIPTION: Represents the event of updating a description.
 * </ul>
 */
public enum Event {
  DARK_MODE_TOGGLED,
  CHAOS_GAME_DESCRIPTION_UPDATED,
  CANVAS_SIZE_UPDATED,
  SET_PRESET,
  RUN_CHAOS_GAME,
  STEPS_RAN,
  LOAD_FILE,
  SAVE_FILE,
  OPEN_EDIT_DESCRIPTION_DIALOG,
  UPDATE_DESCRIPTION;
}
