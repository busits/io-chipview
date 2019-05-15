package io.views.chip.tag;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChipTagView extends FrameLayout {

    TextView hintTxt;
    ViewFlipper viewFlipper;
    FlowLayout flowLayout;

    boolean chip_closable;
    int chip_background_color, chip_text_color, chip_close_color;
    ChipStyle chipStyle;
    Listener listener;

    public enum ChipStyle {
        circular_with_image, circular_text_only, rect_with_image, rect_text_only
    }


    public interface Listener {
        public void onImageReady(Chip chip, ImageView icon);

        public void onChipRemoved(Chip chip);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public ChipTagView(@NonNull Context context) {
        super(context);
        chip_background_color = ContextCompat.getColor(getContext(), R.color.chip_background_color);
        chip_text_color = ContextCompat.getColor(getContext(), R.color.chip_text_color);
        chip_close_color = ContextCompat.getColor(getContext(), R.color.chip_close_color);
        chip_closable = true;
        chipStyle = ChipStyle.rect_text_only;
        init(null);
    }

    public ChipTagView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }


    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        hintTxt.setOnClickListener(l);
    }

    void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.chip_tag_view, this);
        hintTxt = findViewById(R.id.hint_textview);
        viewFlipper = findViewById(R.id.chip_flipper);
        flowLayout = findViewById(R.id.chip_tag_flow_layout);
        if (attrs != null) {
            TypedArray ta = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.IoChipTagView, 0, 0);
            hintTxt.setText(ta.getString(R.styleable.IoChipTagView_chip_hint));
            chip_background_color = ta.getColor(R.styleable.IoChipTagView_chip_background_color, ContextCompat.getColor(getContext(), R.color.chip_background_color));
            chip_text_color = ta.getColor(R.styleable.IoChipTagView_chip_text_color, ContextCompat.getColor(getContext(), R.color.chip_text_color));
            chip_close_color = ta.getColor(R.styleable.IoChipTagView_chip_close_color, ContextCompat.getColor(getContext(), R.color.chip_close_color));
            chip_closable = ta.getBoolean(R.styleable.IoChipTagView_chip_closable, true);
            chipStyle = ChipStyle.values()[ta.getInt(R.styleable.IoChipTagView_chip_style, 0)];
            ta.recycle();
        }
    }

    public Chip getChip(long id) {
        for (int i = 0; i < flowLayout.getChildCount(); i++) {
            Chip tagModel = (Chip) flowLayout.getChildAt(i).getTag();
            if (tagModel.id == id)
                return tagModel;
        }
        return null;
    }

    public void addChip(final Chip chip) {
        if (getChip(chip.id) == null) {
            View chipLayout;
            TextView chipText;
            ViewGroup layoutContainer;
            ImageView secondIcon;
            ImageView firstIcon;
            switch (chipStyle) {
                case circular_with_image:
                    chipLayout = LayoutInflater.from(getContext()).inflate(R.layout.chip_circular_with_image, null);
                    chipLayout.setTag(chip);
                    chipText = chipLayout.findViewById(R.id.io_chip_text);
                    chipText.setTextColor(chip_text_color);
                    layoutContainer = chipLayout.findViewById(R.id.io_chip_layout);
                    layoutContainer.setBackgroundTintList(ColorStateList.valueOf(chip_background_color));
                    secondIcon = chipLayout.findViewById(R.id.io_secondary_icon);
                    secondIcon.setColorFilter(chip_close_color);
                    secondIcon.setVisibility(chip_closable ? VISIBLE : GONE);
                    secondIcon.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            removeChip(chip.id);
                            if (listener != null)
                                listener.onChipRemoved(chip);
                        }
                    });
                    firstIcon = chipLayout.findViewById(R.id.io_image_icon);
                    chipText.setText(chip.name);
                    flowLayout.addView(chipLayout);
                    if (listener != null)
                        listener.onImageReady(chip, firstIcon);
                    break;
                case circular_text_only:
                    chipLayout = LayoutInflater.from(getContext()).inflate(R.layout.chip_circulat_text_only, null);
                    chipLayout.setTag(chip);
                    chipText = chipLayout.findViewById(R.id.io_chip_text);
                    chipText.setTextColor(chip_text_color);
                    layoutContainer = chipLayout.findViewById(R.id.io_chip_layout);
                    layoutContainer.setBackgroundTintList(ColorStateList.valueOf(chip_background_color));
                    secondIcon = chipLayout.findViewById(R.id.io_secondary_icon);
                    secondIcon.setColorFilter(chip_close_color);
                    secondIcon.setVisibility(chip_closable ? VISIBLE : GONE);
                    secondIcon.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            removeChip(chip.id);
                            if (listener != null)
                                listener.onChipRemoved(chip);
                        }
                    });
                    chipText.setText(chip.name);
                    flowLayout.addView(chipLayout);
                    break;
                case rect_text_only:
                    chipLayout = LayoutInflater.from(getContext()).inflate(R.layout.chip_rect_text_only, null);
                    chipLayout.setTag(chip);
                    chipText = chipLayout.findViewById(R.id.io_chip_text);
                    chipText.setTextColor(chip_text_color);
                    layoutContainer = chipLayout.findViewById(R.id.io_chip_layout);
                    layoutContainer.setBackgroundTintList(ColorStateList.valueOf(chip_background_color));
                    secondIcon = chipLayout.findViewById(R.id.io_secondary_icon);
                    secondIcon.setColorFilter(chip_close_color);
                    secondIcon.setVisibility(chip_closable ? VISIBLE : GONE);
                    secondIcon.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            removeChip(chip.id);
                            if (listener != null)
                                listener.onChipRemoved(chip);
                        }
                    });
                    chipText.setText(chip.name);
                    flowLayout.addView(chipLayout);
                    break;

                case rect_with_image:
                    chipLayout = LayoutInflater.from(getContext()).inflate(R.layout.chip_rect_with_image, null);
                    chipLayout.setTag(chip);
                    chipText = chipLayout.findViewById(R.id.io_chip_text);
                    chipText.setTextColor(chip_text_color);
                    layoutContainer = chipLayout.findViewById(R.id.io_chip_layout);
                    layoutContainer.setBackgroundTintList(ColorStateList.valueOf(chip_background_color));
                    secondIcon = chipLayout.findViewById(R.id.io_secondary_icon);
                    secondIcon.setColorFilter(chip_close_color);
                    secondIcon.setVisibility(chip_closable ? VISIBLE : GONE);
                    secondIcon.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            removeChip(chip.id);
                            if (listener != null)
                                listener.onChipRemoved(chip);
                        }
                    });
                    firstIcon = chipLayout.findViewById(R.id.io_image_icon);
                    chipText.setText(chip.name);
                    flowLayout.addView(chipLayout);
                    if (listener != null)
                        listener.onImageReady(chip, firstIcon);
                    break;
            }
            invalidateHint();
        }
    }

    public void removeChip(long id) {
        for (int i = 0; i < flowLayout.getChildCount(); i++) {
            Chip tagModel = (Chip) flowLayout.getChildAt(i).getTag();
            if (tagModel.id == id) {
                flowLayout.removeViewAt(i);
                invalidateHint();
            }
        }
    }

    public Long[] getChipIds() {
        Long[] ids = new Long[flowLayout.getChildCount()];
        for (int i = 0; i < flowLayout.getChildCount(); i++) {
            Chip tagModel = (Chip) flowLayout.getChildAt(i).getTag();
            ids[i] = tagModel.id;
        }
        return ids;
    }

    public List<Long> getChipIdsList() {
        return new ArrayList<>(Arrays.asList(getChipIds()));
    }

    public Chip[] getChips() {
        Chip[] tags = new Chip[flowLayout.getChildCount()];
        for (int i = 0; i < flowLayout.getChildCount(); i++) {
            tags[i] = (Chip) flowLayout.getChildAt(i).getTag();
        }
        return tags;
    }
    public List<Chip> getChipList() {
        return new ArrayList<>(Arrays.asList(getChips()));
    }

    private void invalidateHint() {
        if (flowLayout.getChildCount() == 0)
            viewFlipper.setDisplayedChild(0);
        else
            viewFlipper.setDisplayedChild(1);
    }

}
