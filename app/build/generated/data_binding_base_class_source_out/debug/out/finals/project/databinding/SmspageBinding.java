// Generated by view binder compiler. Do not edit!
package finals.project.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import finals.project.R;
import io.getstream.chat.android.ui.channel.list.ChannelListView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class SmspageBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ChannelListView channelListView;

  private SmspageBinding(@NonNull ConstraintLayout rootView,
      @NonNull ChannelListView channelListView) {
    this.rootView = rootView;
    this.channelListView = channelListView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static SmspageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SmspageBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.smspage, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SmspageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.channelListView;
      ChannelListView channelListView = ViewBindings.findChildViewById(rootView, id);
      if (channelListView == null) {
        break missingId;
      }

      return new SmspageBinding((ConstraintLayout) rootView, channelListView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}