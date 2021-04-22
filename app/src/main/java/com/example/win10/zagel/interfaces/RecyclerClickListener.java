package com.example.win10.zagel.interfaces;

public interface RecyclerClickListener {

    void recyclerViewListClicked(int position);

    void setFav(int pos, int newsID, boolean isSelect);

    void setReadLater(int pos, int newsID, boolean isSelect);
}
