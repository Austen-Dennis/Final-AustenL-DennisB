// Generated by view binder compiler. Do not edit!
package finals.project.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import finals.project.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLatestMessagesBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button home;

  @NonNull
  public final Button newMessage;

  @NonNull
  public final RecyclerView newestMessages;

  @NonNull
  public final Button profile;

  @NonNull
  public final Toolbar toolbar2;

  private ActivityLatestMessagesBinding(@NonNull ConstraintLayout rootView, @NonNull Button home,
      @NonNull Button newMessage, @NonNull RecyclerView newestMessages, @NonNull Button profile,
      @NonNull Toolbar toolbar2) {
    this.rootView = rootView;
    this.home = home;
    this.newMessage = newMessage;
    this.newestMessages = newestMessages;
    this.profile = profile;
    this.toolbar2 = toolbar2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLatestMessagesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLatestMessagesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_latest_messages, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLatestMessagesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.home;
      Button home = ViewBindings.findChildViewById(rootView, id);
      if (home == null) {
        break missingId;
      }

      id = R.id.new_message;
      Button newMessage = ViewBindings.findChildViewById(rootView, id);
      if (newMessage == null) {
        break missingId;
      }

      id = R.id.newest_Messages;
      RecyclerView newestMessages = ViewBindings.findChildViewById(rootView, id);
      if (newestMessages == null) {
        break missingId;
      }

      id = R.id.profile;
      Button profile = ViewBindings.findChildViewById(rootView, id);
      if (profile == null) {
        break missingId;
      }

      id = R.id.toolbar2;
      Toolbar toolbar2 = ViewBindings.findChildViewById(rootView, id);
      if (toolbar2 == null) {
        break missingId;
      }

      return new ActivityLatestMessagesBinding((ConstraintLayout) rootView, home, newMessage,
          newestMessages, profile, toolbar2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
