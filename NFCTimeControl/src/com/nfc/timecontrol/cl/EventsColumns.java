package com.nfc.timecontrol.cl;

import android.net.Uri;
import android.provider.BaseColumns;


public final class EventsColumns implements BaseColumns {

    // This class cannot be instantiated
    private EventsColumns() {}

    
    /**
     * The content:// style URL for this table
     */
    public static final Uri CONTENT_URI = Uri.parse("content://" + TagEventProvider.AUTHORITY + "/events");

    /**
     * The MIME type of {@link #CONTENT_URI} providing a directory of notes.
     */
    public static final String CONTENT_TYPE = "vnd.nfc.timecontrol.cursor.dir/vnd.timecontrol.event";

    /**
     * The MIME type of a {@link #CONTENT_URI} sub-directory of a single note.
     */
    public static final String CONTENT_ITEM_TYPE = "vnd.nfc.timecontrol.cursor.item/vnd.timecontrol.event";

    /**
     * The default sort order for this table
     */
    public static final String DEFAULT_SORT_ORDER = "created DESC";

    /**
     * User name
     * <P>Type: TEXT</P>
     */
    public static final String USER = "user";

    /**
     * tag ID
     * <P>Type: TEXT</P>
     */
    public static final String TAG_ID = "tagID";

    /**
     * The token of particular event
     * <P>Type: TEXT</P>
     */
    public static final String TOKEN = "token";
    
    /**
     * The token of particular event
     * <P>Type: TEXT</P>
     */
    public static final String COMMENT = "comment";
    
    /**
     * The timestamp for when the note was created
     * <P>Type: INTEGER (long from System.curentTimeMillis())</P>
     */
    public static final String CREATED_DATE = "created";
    
    /**
     * The timestamp for when the note was created
     * <P>Type: INTEGER</P>
     */
    public static final String STATE = "state";

	public static final int COMMENT_INDEX = 4;
	public static final int CREATED_DATE_INDEX = 5;
}
