package com.slamingdev.daniillerusse;

/**
 * Created by Cyril on 12/02/2016.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public final class MyAdapter extends BaseAdapter {
    private final List<Item> mItems = new ArrayList<Item>(30);
    private final LayoutInflater mInflater;
    public Context context;


    public MyAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;

        mItems.add(new Item("Entre vrai bonhommes",       R.drawable.entrevraibonhommes));
        mItems.add(new Item("Gardez la monnaie",   R.drawable.gardezlamonnaie));
        mItems.add(new Item("Je m'en bat les couilles",   R.drawable.jemenbaslescouilles));
        mItems.add(new Item("Je simulais",   R.drawable.jesimulais));
        mItems.add(new Item("Tout seul chez soi",   R.drawable.toutseulchezsoi));
        mItems.add(new Item("Tout va bien",   R.drawable.toutvasbien));
        mItems.add(new Item("Appelle moi le gros",   R.drawable.appellemoilegros));
        mItems.add(new Item("Je suis qu'un turc",   R.drawable.jesuisquunturc));
        mItems.add(new Item("Qu'est ce que tu vas faire ?",   R.drawable.questcequetuvasfaire));
        mItems.add(new Item("Tu veux juste une baguette",   R.drawable.tuveuxjusteunebaguette));


    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        //ImageView picture;
        //TextView name;
        ViewHolderItem viewHolder;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);

            viewHolder = new ViewHolderItem();
            viewHolder.textViewItem = (TextView) v.findViewById(R.id.text);
            viewHolder.imageViewItem = (ImageView) v.findViewById(R.id.picture);
            v.setTag(viewHolder);

            //v.setTag(R.id.picture, v.findViewById(R.id.picture));
            //v.setTag(R.id.text, v.findViewById(R.id.text));

        }
        else{
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            viewHolder = (ViewHolderItem) v.getTag();
        }

        // object item based on the position
        Item item = getItem(i);

        // assign values if the object is not null
        if(item != null) {
            // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values
            viewHolder.textViewItem.setText(item.name);
            viewHolder.textViewItem.setTag(item.drawableId);
            viewHolder.imageViewItem.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(),item.drawableId, 100, 100));
            viewHolder.imageViewItem.setTag(item.drawableId);
        }

        return v;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static class Item {
        public final String name;
        public final int drawableId;

        Item(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }

    // our ViewHolder.
    // caches our TextView
    private static class ViewHolderItem {
        TextView textViewItem;
        ImageView imageViewItem;
    }

}
