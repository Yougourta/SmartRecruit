package fr.smartrecruit.view.fragments.recruteur;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.controller.recruiter.MyAppointmentsAdapter;
import fr.smartrecruit.controller.recruiter.MyAppointmentsController;
import fr.smartrecruit.data.RecAppointment;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MyAppointmentsFragment extends Fragment {
    private RecyclerView appointmentsRecycler;
    private MyAppointmentsAdapter appointmentsAdapter;
    private List<RecAppointment> appointments;

    public String aa = "";
    public String mm = "";
    public String jj = "";

    public String hh = "";
    public String mnt = "";

    public MyAppointmentsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_appointments, container, false);
        findViews(view);
        initAdapter();
        refresh(view);
        return view;
    }

    public void findViews(View view){
        appointmentsRecycler = view.findViewById(R.id.listRecAppointments);
    }

    public void initAdapter(){
        final Context context = getContext();
        appointments = MyAppointmentsController.getMyAppointmentsController().getAppointments(context);
        appointmentsAdapter = new MyAppointmentsAdapter(appointments);
        appointmentsRecycler.setAdapter(appointmentsAdapter);
        appointmentsRecycler.setHasFixedSize(true);
        MyAppointmentsController.getMyAppointmentsController().setApiAdapter(appointmentsAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        appointmentsRecycler.setLayoutManager(llm);
        appointmentsRecycler.setItemAnimator(new DefaultItemAnimator());
        appointmentsRecycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Reject application");
                    builder.setMessage("Do you really want to reject this application ?");
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MyAppointmentsController.getMyAppointmentsController().rejectApplication(appointments.get(position).getOffer());
                                    appointmentsAdapter.removeItem(position);
                                    dialog.dismiss();
                                }
                            });
                    builder.setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    appointmentsAdapter.notifyDataSetChanged();
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else if (direction == ItemTouchHelper.RIGHT){
                    Calendar mcurrentTime = Calendar.getInstance();
                    int mYear = mcurrentTime.get(Calendar.YEAR); // current year
                    int mMonth = mcurrentTime.get(Calendar.MONTH); // current month
                    int mDay = mcurrentTime.get(Calendar.DAY_OF_MONTH); // current day
                    DatePickerDialog mDatePicker;
                    mDatePicker = new DatePickerDialog(context, R.style.datepicker,new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            aa = String.valueOf(year);
                            mm = String.format("%02d", month+1);
                            jj = String.valueOf(dayOfMonth);
                            Calendar mcurrentTime2 = Calendar.getInstance();
                            int hour = mcurrentTime2.get(Calendar.HOUR_OF_DAY);
                            int minute = mcurrentTime2.get(Calendar.MINUTE);
                            TimePickerDialog mTimePicker;
                            mTimePicker = new TimePickerDialog(context, R.style.datepicker, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                    hh = String.valueOf(selectedHour);
                                    mnt = String.valueOf(selectedMinute);
                                    // send appointment
                                    final String date = jj+"/"+mm+"/"+aa;
                                    final String time = hh+":"+mnt;
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setCancelable(true);
                                    builder.setTitle("Schedule appointment");
                                    builder.setMessage("Schedule appoint for :\n"+"Date: "+date+" at "+time);
                                    builder.setPositiveButton("Yes",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    MyAppointmentsController.getMyAppointmentsController().setAppointment(appointments.get(position).getOffer(), date, time);
                                                    appointmentsAdapter.removeItem(position);
                                                    dialog.dismiss();
                                                }
                                            });
                                    builder.setNegativeButton("No",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    appointmentsAdapter.notifyDataSetChanged();
                                                    dialog.dismiss();
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                }
                            }, hour, minute, true);//Yes 24 hour time
                            mTimePicker.show();
                        }
                    }, mYear, mMonth, mDay);
                    mDatePicker.show();
                    appointmentsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(context, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, R.color.deleteColor))
                        .addSwipeLeftActionIcon(R.drawable.ic_delete_white_36dp)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(context, R.color.favoriteColor))
                        .addSwipeRightActionIcon(R.drawable.ic_check_white_36dp)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(appointmentsRecycler);
    }

    private void refresh(View view){
        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_to_refresh_recappointments);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MyAppointmentsController.getMyAppointmentsController().refreshAppointments();
                appointmentsAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
