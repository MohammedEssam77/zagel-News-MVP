package com.example.win10.zagel.view;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.win10.zagel.R;
import com.example.win10.zagel.interfaces.DetailsContract;
import com.example.win10.zagel.models.DetailsModel;
import com.example.win10.zagel.models.NewsModel;
import com.example.win10.zagel.networks.Constants;
import com.example.win10.zagel.networks.DetailsNetwork;
import com.example.win10.zagel.presenter.DetailsPresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends Activity implements DetailsContract.DetailsView {
    @BindView(R.id.sourceTx)
    TextView sourceTxt;
    @BindView(R.id.sourceImg)
    ImageView sourceImg;
    @BindView(R.id.btnSectionTitl)
    Button btnSectionTitle;
    @BindView(R.id.coverImg)
    ImageView coverImage;
    @BindView(R.id.dateTxt)
    TextView dateTxt;
    @BindView(R.id.calendarImg)
    ImageView calendarImg;
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    NewsModel newsModel;
    Context context;
    private DetailsContract.DetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        presenter = new DetailsPresenter(this, new DetailsNetwork());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            newsModel = bundle.getParcelable("myNewsObj");
            presenter.requestData(newsModel);
        }

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setImage(ImageView image, NewsModel newsModel) {
        if (newsModel.getRatio() == null || newsModel.getRatio().equals("") ||
                newsModel.getRatio().equals("1.55") || newsModel.getRatio().equals("0")) {
            image.setVisibility(View.GONE);
        } else {
            WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics();
            display.getMetrics(outMetrics);
            int width = outMetrics.widthPixels;
            int height = (int) (width / Double.parseDouble(newsModel.getRatio()));

            image.setVisibility(View.VISIBLE);
            image.getLayoutParams().width = width;
            image.getLayoutParams().height = height;

            Picasso.with(context)
                    .load(Uri.parse(Constants.mediaURL + newsModel.getImg()))
                    .placeholder(R.drawable.placeholder_img)
                    .into(image);
        }
    }

    @Override
    public void dataResponse(DetailsModel detailsModel) {
        sourceTxt.setText(detailsModel.getSectionTitle());
        textTitle.setText(detailsModel.getBreif());
        btnSectionTitle.setText(detailsModel.getSectionTitle());
        dateTxt.setText(detailsModel.getFormatedDate());
        setImage(coverImage, newsModel);
        Picasso.with(context)
                .load(Uri.parse(Constants.loadSourceImg(newsModel.getSourceID())))
                .into(sourceImg);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }
}