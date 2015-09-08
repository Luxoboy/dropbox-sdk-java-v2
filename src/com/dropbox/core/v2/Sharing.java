/* DO NOT EDIT */
/* This file was generated from sharing.babel */

package com.dropbox.core.v2;

import java.io.IOException;
import java.util.regex.Pattern;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.dropbox.core.DbxApiException;
import com.dropbox.core.v2.DbxRawClientV2;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestUtil;
import com.dropbox.core.http.HttpRequestor;
import com.dropbox.core.json.JsonArrayReader;
import com.dropbox.core.json.JsonDateReader;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.json.JsonReadException;
import com.dropbox.core.json.JsonWriter;

/**
 * Classes and routes in namespace "sharing".
 */
public final class Sharing {
    // namespace sharing

    private final DbxRawClientV2 client;

    Sharing(DbxRawClientV2 client) {
        this.client = client;
    }

    /**
     * Who can access a shared link. The most open visibility is {@code
     * public_}. The default depends on many aspects, such as team and user
     * preferences and shared folder settings.
     */
    public enum Visibility {
        // union Visibility
        /**
         * Anyone who has received the link can access it. No login required.
         */
        public_,
        /**
         * Only members of the same DfB (Dropbox for Business) team can access
         * the link. Login is required.
         */
        teamOnly,
        /**
         * A link-specific password is required to access the link. Login is not
         * required.
         */
        password,
        /**
         * Only members of the same DfB (Dropbox for Business) team who have the
         * link-specific password can access the link.
         */
        teamAndPassword,
        /**
         * Only members of the shared folder containing the linked file can
         * access the link. Login is required.
         */
        sharedFolderOnly,
        /**
         * An unknown restriction is in place.
         */
        other;  // *catch_all

        static final JsonWriter<Visibility> _writer = new JsonWriter<Visibility>()
        {
            public void write(Visibility x, JsonGenerator g)
             throws IOException
            {
                switch (x) {
                    case public_:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("public");
                        g.writeEndObject();
                        break;
                    case teamOnly:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("team_only");
                        g.writeEndObject();
                        break;
                    case password:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("password");
                        g.writeEndObject();
                        break;
                    case teamAndPassword:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("team_and_password");
                        g.writeEndObject();
                        break;
                    case sharedFolderOnly:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("shared_folder_only");
                        g.writeEndObject();
                        break;
                    case other:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("other");
                        g.writeEndObject();
                        break;
                }
            }
        };

        public static final JsonReader<Visibility> _reader = new JsonReader<Visibility>()
        {
            public final Visibility read(JsonParser parser)
                throws IOException, JsonReadException
            {
                return JsonReader.readEnum(parser, _values, other);
            }
        };
        private static final java.util.HashMap<String,Visibility> _values;
        static {
            _values = new java.util.HashMap<String,Visibility>();
            _values.put("public", public_);
            _values.put("team_only", teamOnly);
            _values.put("password", password);
            _values.put("team_and_password", teamAndPassword);
            _values.put("shared_folder_only", sharedFolderOnly);
            _values.put("other", other);
        }

        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static Visibility fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * Metadata for a shared link. This can be either a {@link PathLinkMetadata}
     * or {@link CollectionLinkMetadata}.
     */
    public static class LinkMetadata {
        // struct LinkMetadata
        /**
         * URL of the shared link.
         */
        public final String url;
        /**
         * Who can access the link.
         */
        public final Visibility visibility;
        /**
         * Expiration time, if set. By default the link won't expire.
         */
        public final java.util.Date expires;

        public LinkMetadata(String url, Visibility visibility, java.util.Date expires) {
            this.url = url;
            if (url == null) {
                throw new RuntimeException("Required value for 'url' is null");
            }
            this.visibility = visibility;
            if (visibility == null) {
                throw new RuntimeException("Required value for 'visibility' is null");
            }
            this.expires = expires;
        }
        public JsonWriter getWriter() {
            return LinkMetadata._writer;
        }
        static final JsonWriter<LinkMetadata> _writer = new JsonWriter<LinkMetadata>()
        {
            public final void write(LinkMetadata x, JsonGenerator g)
             throws IOException
            {
                JsonWriter w = x.getWriter();
                if (w != this) {
                    w.write(x, g);
                    return;
                }
                g.writeStartObject();
                LinkMetadata._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(LinkMetadata x, JsonGenerator g)
             throws IOException
            {
                g.writeStringField("url", x.url);
                g.writeFieldName("visibility");
                Visibility._writer.write(x.visibility, g);
                if (x.expires != null) {
                    g.writeFieldName("expires");
                    writeDateIso(x.expires, g);
                }
            }
        };

        public static final JsonReader<LinkMetadata> _reader = new JsonReader<LinkMetadata>() {

            public final LinkMetadata read(JsonParser parser)
                throws IOException, JsonReadException
            {
                LinkMetadata result;
                JsonReader.expectObjectStart(parser);
                String[] tags = readTags(parser);
                result = readFromTags(tags, parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final LinkMetadata readFromTags(String[] tags, JsonParser parser)
                throws IOException, JsonReadException
            {
                if (tags != null && tags.length > 0) {
                    if ("path".equals(tags[0])) {
                        return PathLinkMetadata._reader.readFromTags(tags, parser);
                    }
                    if ("collection".equals(tags[0])) {
                        return CollectionLinkMetadata._reader.readFromTags(tags, parser);
                    }
                    // If no match, fall back to base class
                }
                return readFields(parser);
            }

            public final LinkMetadata readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                String url = null;
                Visibility visibility = null;
                java.util.Date expires = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("url".equals(fieldName)) {
                        url = JsonReader.StringReader
                            .readField(parser, "url", url);
                    }
                    else if ("visibility".equals(fieldName)) {
                        visibility = Visibility._reader
                            .readField(parser, "visibility", visibility);
                    }
                    else if ("expires".equals(fieldName)) {
                        expires = JsonDateReader.DropboxV2
                            .readField(parser, "expires", expires);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (url == null) {
                    throw new JsonReadException("Required field \"url\" is missing.", parser.getTokenLocation());
                }
                if (visibility == null) {
                    throw new JsonReadException("Required field \"visibility\" is missing.", parser.getTokenLocation());
                }
                return new LinkMetadata(url, visibility, expires);
            }
        };

        public String toString() {
            return "LinkMetadata." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "LinkMetadata." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static LinkMetadata fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * Metadata for a path-based shared link.
     */
    public static class PathLinkMetadata extends LinkMetadata  {
        // struct PathLinkMetadata
        /**
         * Path in user's Dropbox.
         */
        public final String path;

        public PathLinkMetadata(String url, Visibility visibility, String path, java.util.Date expires) {
            super(url, visibility, expires);
            this.path = path;
            if (path == null) {
                throw new RuntimeException("Required value for 'path' is null");
            }
        }
        public JsonWriter getWriter() {
            return PathLinkMetadata._writer;
        }
        static final JsonWriter<PathLinkMetadata> _writer = new JsonWriter<PathLinkMetadata>()
        {
            public final void write(PathLinkMetadata x, JsonGenerator g)
             throws IOException
            {
                JsonWriter w = x.getWriter();
                if (w != this) {
                    w.write(x, g);
                    return;
                }
                g.writeStartObject();
                g.writeStringField(".tag", "path");
                LinkMetadata._writer.writeFields(x, g);
                PathLinkMetadata._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(PathLinkMetadata x, JsonGenerator g)
             throws IOException
            {
                g.writeStringField("path", x.path);
            }
        };

        public static final JsonReader<PathLinkMetadata> _reader = new JsonReader<PathLinkMetadata>() {

            public final PathLinkMetadata read(JsonParser parser)
                throws IOException, JsonReadException
            {
                PathLinkMetadata result;
                JsonReader.expectObjectStart(parser);
                String[] tags = readTags(parser);
                result = readFromTags(tags, parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final PathLinkMetadata readFromTags(String[] tags, JsonParser parser)
                throws IOException, JsonReadException
            {
                if (tags != null) {
                    assert tags.length >= 1;
                    assert "path".equals(tags[0]);
                }
                return readFields(parser);
            }

            public final PathLinkMetadata readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                String url = null;
                Visibility visibility = null;
                String path = null;
                java.util.Date expires = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("url".equals(fieldName)) {
                        url = JsonReader.StringReader
                            .readField(parser, "url", url);
                    }
                    else if ("visibility".equals(fieldName)) {
                        visibility = Visibility._reader
                            .readField(parser, "visibility", visibility);
                    }
                    else if ("path".equals(fieldName)) {
                        path = JsonReader.StringReader
                            .readField(parser, "path", path);
                    }
                    else if ("expires".equals(fieldName)) {
                        expires = JsonDateReader.DropboxV2
                            .readField(parser, "expires", expires);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (url == null) {
                    throw new JsonReadException("Required field \"url\" is missing.", parser.getTokenLocation());
                }
                if (visibility == null) {
                    throw new JsonReadException("Required field \"visibility\" is missing.", parser.getTokenLocation());
                }
                if (path == null) {
                    throw new JsonReadException("Required field \"path\" is missing.", parser.getTokenLocation());
                }
                return new PathLinkMetadata(url, visibility, path, expires);
            }
        };

        public String toString() {
            return "PathLinkMetadata." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "PathLinkMetadata." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static PathLinkMetadata fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * Metadata for a collection-based shared link.
     */
    public static class CollectionLinkMetadata extends LinkMetadata  {
        // struct CollectionLinkMetadata

        public CollectionLinkMetadata(String url, Visibility visibility, java.util.Date expires) {
            super(url, visibility, expires);
        }
        public JsonWriter getWriter() {
            return CollectionLinkMetadata._writer;
        }
        static final JsonWriter<CollectionLinkMetadata> _writer = new JsonWriter<CollectionLinkMetadata>()
        {
            public final void write(CollectionLinkMetadata x, JsonGenerator g)
             throws IOException
            {
                JsonWriter w = x.getWriter();
                if (w != this) {
                    w.write(x, g);
                    return;
                }
                g.writeStartObject();
                g.writeStringField(".tag", "collection");
                LinkMetadata._writer.writeFields(x, g);
                CollectionLinkMetadata._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(CollectionLinkMetadata x, JsonGenerator g)
             throws IOException
            {
            }
        };

        public static final JsonReader<CollectionLinkMetadata> _reader = new JsonReader<CollectionLinkMetadata>() {

            public final CollectionLinkMetadata read(JsonParser parser)
                throws IOException, JsonReadException
            {
                CollectionLinkMetadata result;
                JsonReader.expectObjectStart(parser);
                String[] tags = readTags(parser);
                result = readFromTags(tags, parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final CollectionLinkMetadata readFromTags(String[] tags, JsonParser parser)
                throws IOException, JsonReadException
            {
                if (tags != null) {
                    assert tags.length >= 1;
                    assert "collection".equals(tags[0]);
                }
                return readFields(parser);
            }

            public final CollectionLinkMetadata readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                String url = null;
                Visibility visibility = null;
                java.util.Date expires = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("url".equals(fieldName)) {
                        url = JsonReader.StringReader
                            .readField(parser, "url", url);
                    }
                    else if ("visibility".equals(fieldName)) {
                        visibility = Visibility._reader
                            .readField(parser, "visibility", visibility);
                    }
                    else if ("expires".equals(fieldName)) {
                        expires = JsonDateReader.DropboxV2
                            .readField(parser, "expires", expires);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (url == null) {
                    throw new JsonReadException("Required field \"url\" is missing.", parser.getTokenLocation());
                }
                if (visibility == null) {
                    throw new JsonReadException("Required field \"visibility\" is missing.", parser.getTokenLocation());
                }
                return new CollectionLinkMetadata(url, visibility, expires);
            }
        };

        public String toString() {
            return "CollectionLinkMetadata." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "CollectionLinkMetadata." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static CollectionLinkMetadata fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * Arguments for {@link #getSharedLinks}.
     */
    public static class GetSharedLinksArg {
        // struct GetSharedLinksArg
        /**
         * See {@link #getSharedLinks} description.
         */
        public final String path;

        public GetSharedLinksArg(String path) {
            this.path = path;
        }
        static final JsonWriter<GetSharedLinksArg> _writer = new JsonWriter<GetSharedLinksArg>()
        {
            public final void write(GetSharedLinksArg x, JsonGenerator g)
             throws IOException
            {
                g.writeStartObject();
                GetSharedLinksArg._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(GetSharedLinksArg x, JsonGenerator g)
             throws IOException
            {
                if (x.path != null) {
                    g.writeFieldName("path");
                    g.writeString(x.path);
                }
            }
        };

        public static final JsonReader<GetSharedLinksArg> _reader = new JsonReader<GetSharedLinksArg>() {

            public final GetSharedLinksArg read(JsonParser parser)
                throws IOException, JsonReadException
            {
                GetSharedLinksArg result;
                JsonReader.expectObjectStart(parser);
                result = readFields(parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final GetSharedLinksArg readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                String path = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("path".equals(fieldName)) {
                        path = JsonReader.StringReader
                            .readField(parser, "path", path);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                return new GetSharedLinksArg(path);
            }
        };

        public String toString() {
            return "GetSharedLinksArg." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "GetSharedLinksArg." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static GetSharedLinksArg fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * Result for {@link #getSharedLinks}.
     */
    public static class GetSharedLinksResult {
        // struct GetSharedLinksResult
        /**
         * Shared links applicable to the path argument.
         */
        public final java.util.ArrayList<LinkMetadata> links;

        public GetSharedLinksResult(java.util.ArrayList<LinkMetadata> links) {
            this.links = links;
            if (links == null) {
                throw new RuntimeException("Required value for 'links' is null");
            }
            for (LinkMetadata x : links) {
                if (x == null) {
                    throw new RuntimeException("An item in list 'links' is null");
                }
            }
        }
        static final JsonWriter<GetSharedLinksResult> _writer = new JsonWriter<GetSharedLinksResult>()
        {
            public final void write(GetSharedLinksResult x, JsonGenerator g)
             throws IOException
            {
                g.writeStartObject();
                GetSharedLinksResult._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(GetSharedLinksResult x, JsonGenerator g)
             throws IOException
            {
                g.writeFieldName("links");
                g.writeStartArray();
                for (LinkMetadata item: x.links) {
                    if (item != null) {
                        LinkMetadata._writer.write(item, g);
                    }
                }
                g.writeEndArray();
            }
        };

        public static final JsonReader<GetSharedLinksResult> _reader = new JsonReader<GetSharedLinksResult>() {

            public final GetSharedLinksResult read(JsonParser parser)
                throws IOException, JsonReadException
            {
                GetSharedLinksResult result;
                JsonReader.expectObjectStart(parser);
                result = readFields(parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final GetSharedLinksResult readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                java.util.ArrayList<LinkMetadata> links = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("links".equals(fieldName)) {
                        links = JsonArrayReader.mk(LinkMetadata._reader)
                            .readField(parser, "links", links);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (links == null) {
                    throw new JsonReadException("Required field \"links\" is missing.", parser.getTokenLocation());
                }
                return new GetSharedLinksResult(links);
            }
        };

        public String toString() {
            return "GetSharedLinksResult." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "GetSharedLinksResult." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static GetSharedLinksResult fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }


    public static final class GetSharedLinksError {
        // union GetSharedLinksError

        /**
         * The discriminating tag type for {@link GetSharedLinksError}.
         */
        public enum Tag {
            path,  // Nullable
            other  // *catch_all
        }

        /**
         * The discriminating tag for this instance.
         */
        public final Tag tag;

        private final String pathValue;
        private GetSharedLinksError(Tag t, String v) {
            tag = t;
            pathValue = v;
            validate();
        }
        public static GetSharedLinksError path(String v) {
            return new GetSharedLinksError(Tag.path, v);
        }
        public String getPath() {
            if (tag != Tag.path) {
                throw new RuntimeException("getPath() requires tag==path, actual tag=="+tag);
            }
            return pathValue;
        }

        public static final GetSharedLinksError other = new GetSharedLinksError(Tag.other);

        private GetSharedLinksError(Tag t) {
            tag = t;
            pathValue = null;
            validate();
        }

        private void validate()
        {
            switch (tag) {
                case other:
                    break;
                case path:
                    break;
            }
        }
        static final JsonWriter<GetSharedLinksError> _writer = new JsonWriter<GetSharedLinksError>()
        {
            public final void write(GetSharedLinksError x, JsonGenerator g)
              throws IOException
            {
                switch (x.tag) {
                    case path:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("path");
                        if (x.pathValue != null) {
                            g.writeFieldName("path");
                            g.writeString(x.pathValue);
                        }
                        g.writeEndObject();
                        break;
                    case other:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("other");
                        g.writeEndObject();
                        break;
                }
            }
        };
        public static final JsonReader<GetSharedLinksError> _reader = new JsonReader<GetSharedLinksError>()
        {
            public final GetSharedLinksError read(JsonParser parser)
              throws IOException, JsonReadException
            {
                if (parser.getCurrentToken() == JsonToken.VALUE_STRING) {
                    String text = parser.getText();
                    parser.nextToken();
                    Tag tag = _values.get(text);
                    if (tag == null) { return GetSharedLinksError.other; }
                    switch (tag) {
                        case path: return GetSharedLinksError.path(null);
                        case other: return GetSharedLinksError.other;
                    }
                    throw new JsonReadException("Tag " + tag + " requires a value", parser.getTokenLocation());
                }
                JsonReader.expectObjectStart(parser);
                String[] tags = readTags(parser);
                assert tags != null && tags.length == 1;
                String text = tags[0];
                Tag tag = _values.get(text);
                GetSharedLinksError value = null;
                if (tag != null) {
                    switch (tag) {
                        case path: {
                            if (parser.getCurrentToken() == JsonToken.END_OBJECT) {
                                break;
                            }
                            String v = null;
                            assert parser.getCurrentToken() == JsonToken.FIELD_NAME;
                            text = parser.getText();
                            assert tags[0].equals(text);
                            parser.nextToken();
                            v = JsonReader.StringReader
                                .readField(parser, "path", v);
                            value = GetSharedLinksError.path(v);
                            break;
                        }
                        case other: {
                            value = GetSharedLinksError.other;
                            break;
                        }
                    }
                }
                JsonReader.expectObjectEnd(parser);
                if (value == null) { return GetSharedLinksError.other; }
                return value;
            }

        };
        private static final java.util.HashMap<String,Tag> _values;
        static {
            _values = new java.util.HashMap<String,Tag>();
            _values.put("path", Tag.path);
            _values.put("other", Tag.other);
        }

        public String toString() {
            return "GetSharedLinksError." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "GetSharedLinksError." +  _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static GetSharedLinksError fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * Flag to indicate pending upload default (for linking to not-yet-existing
     * paths).
     */
    public enum PendingUploadMode {
        // union PendingUploadMode
        /**
         * Assume pending uploads are files.
         */
        file,
        /**
         * Assume pending uploads are folders.
         */
        folder;

        static final JsonWriter<PendingUploadMode> _writer = new JsonWriter<PendingUploadMode>()
        {
            public void write(PendingUploadMode x, JsonGenerator g)
             throws IOException
            {
                switch (x) {
                    case file:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("file");
                        g.writeEndObject();
                        break;
                    case folder:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("folder");
                        g.writeEndObject();
                        break;
                }
            }
        };

        public static final JsonReader<PendingUploadMode> _reader = new JsonReader<PendingUploadMode>()
        {
            public final PendingUploadMode read(JsonParser parser)
                throws IOException, JsonReadException
            {
                return JsonReader.readEnum(parser, _values, null);
            }
        };
        private static final java.util.HashMap<String,PendingUploadMode> _values;
        static {
            _values = new java.util.HashMap<String,PendingUploadMode>();
            _values.put("file", file);
            _values.put("folder", folder);
        }

        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static PendingUploadMode fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * Arguments for {@link #createSharedLink}.
     */
    public static class CreateSharedLinkArg {
        // struct CreateSharedLinkArg
        /**
         * The path to share.
         */
        public final String path;
        /**
         * Whether to return a shortened URL.
         */
        public final boolean shortUrl;
        /**
         * If it's okay to share a path that does not yet exist, set this to
         * either 'file' or 'folder' to indicate whether to assume it's a file
         * or folder.
         */
        public final PendingUploadMode pendingUpload;

        public CreateSharedLinkArg(String path, Boolean shortUrl, PendingUploadMode pendingUpload) {
            this.path = path;
            if (path == null) {
                throw new RuntimeException("Required value for 'path' is null");
            }
            if (shortUrl != null) {
                this.shortUrl = shortUrl.booleanValue();
            }
            else {
                this.shortUrl = false;
            }
            this.pendingUpload = pendingUpload;
            if (pendingUpload != null) {
            }
        }
        static final JsonWriter<CreateSharedLinkArg> _writer = new JsonWriter<CreateSharedLinkArg>()
        {
            public final void write(CreateSharedLinkArg x, JsonGenerator g)
             throws IOException
            {
                g.writeStartObject();
                CreateSharedLinkArg._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(CreateSharedLinkArg x, JsonGenerator g)
             throws IOException
            {
                g.writeStringField("path", x.path);
                g.writeBooleanField("short_url", x.shortUrl);
                if (x.pendingUpload != null) {
                    g.writeFieldName("pending_upload");
                    PendingUploadMode._writer.write(x.pendingUpload, g);
                }
            }
        };

        public static final JsonReader<CreateSharedLinkArg> _reader = new JsonReader<CreateSharedLinkArg>() {

            public final CreateSharedLinkArg read(JsonParser parser)
                throws IOException, JsonReadException
            {
                CreateSharedLinkArg result;
                JsonReader.expectObjectStart(parser);
                result = readFields(parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final CreateSharedLinkArg readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                String path = null;
                Boolean shortUrl = null;
                PendingUploadMode pendingUpload = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("path".equals(fieldName)) {
                        path = JsonReader.StringReader
                            .readField(parser, "path", path);
                    }
                    else if ("short_url".equals(fieldName)) {
                        shortUrl = JsonReader.BooleanReader
                            .readField(parser, "short_url", shortUrl);
                    }
                    else if ("pending_upload".equals(fieldName)) {
                        pendingUpload = PendingUploadMode._reader
                            .readField(parser, "pending_upload", pendingUpload);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (path == null) {
                    throw new JsonReadException("Required field \"path\" is missing.", parser.getTokenLocation());
                }
                return new CreateSharedLinkArg(path, shortUrl, pendingUpload);
            }
        };

        public String toString() {
            return "CreateSharedLinkArg." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "CreateSharedLinkArg." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static CreateSharedLinkArg fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }


    public static final class CreateSharedLinkError {
        // union CreateSharedLinkError

        /**
         * The discriminating tag type for {@link CreateSharedLinkError}.
         */
        public enum Tag {
            path,  // LookupError
            other  // *catch_all
        }

        /**
         * The discriminating tag for this instance.
         */
        public final Tag tag;

        private final Files.LookupError pathValue;
        private CreateSharedLinkError(Tag t, Files.LookupError v) {
            tag = t;
            pathValue = v;
            validate();
        }
        public static CreateSharedLinkError path(Files.LookupError v) {
            return new CreateSharedLinkError(Tag.path, v);
        }
        public Files.LookupError getPath() {
            if (tag != Tag.path) {
                throw new RuntimeException("getPath() requires tag==path, actual tag=="+tag);
            }
            return pathValue;
        }

        public static final CreateSharedLinkError other = new CreateSharedLinkError(Tag.other);

        private CreateSharedLinkError(Tag t) {
            tag = t;
            pathValue = null;
            validate();
        }

        private void validate()
        {
            switch (tag) {
                case other:
                    break;
                case path:
                    if (this.pathValue == null) {
                        throw new RuntimeException("Required value for 'path' is null");
                    }
                    break;
            }
        }
        static final JsonWriter<CreateSharedLinkError> _writer = new JsonWriter<CreateSharedLinkError>()
        {
            public final void write(CreateSharedLinkError x, JsonGenerator g)
              throws IOException
            {
                switch (x.tag) {
                    case path:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("path");
                        g.writeFieldName("path");
                        Files.LookupError._writer.write(x.pathValue, g);
                        g.writeEndObject();
                        break;
                    case other:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("other");
                        g.writeEndObject();
                        break;
                }
            }
        };
        public static final JsonReader<CreateSharedLinkError> _reader = new JsonReader<CreateSharedLinkError>()
        {
            public final CreateSharedLinkError read(JsonParser parser)
              throws IOException, JsonReadException
            {
                if (parser.getCurrentToken() == JsonToken.VALUE_STRING) {
                    String text = parser.getText();
                    parser.nextToken();
                    Tag tag = _values.get(text);
                    if (tag == null) { return CreateSharedLinkError.other; }
                    switch (tag) {
                        case other: return CreateSharedLinkError.other;
                    }
                    throw new JsonReadException("Tag " + tag + " requires a value", parser.getTokenLocation());
                }
                JsonReader.expectObjectStart(parser);
                String[] tags = readTags(parser);
                assert tags != null && tags.length == 1;
                String text = tags[0];
                Tag tag = _values.get(text);
                CreateSharedLinkError value = null;
                if (tag != null) {
                    switch (tag) {
                        case path: {
                            Files.LookupError v = null;
                            assert parser.getCurrentToken() == JsonToken.FIELD_NAME;
                            text = parser.getText();
                            assert tags[0].equals(text);
                            parser.nextToken();
                            v = Files.LookupError._reader
                                .readField(parser, "path", v);
                            value = CreateSharedLinkError.path(v);
                            break;
                        }
                        case other: {
                            value = CreateSharedLinkError.other;
                            break;
                        }
                    }
                }
                JsonReader.expectObjectEnd(parser);
                if (value == null) { return CreateSharedLinkError.other; }
                return value;
            }

        };
        private static final java.util.HashMap<String,Tag> _values;
        static {
            _values = new java.util.HashMap<String,Tag>();
            _values.put("path", Tag.path);
            _values.put("other", Tag.other);
        }

        public String toString() {
            return "CreateSharedLinkError." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "CreateSharedLinkError." +  _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static CreateSharedLinkError fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * The access type fo this shared folder. Each type is associated with
     * specific access permission.
     */
    public enum AccessType {
        // union AccessType
        /**
         * The member is the owner of the shared folder. The user can both view
         * and edit the shared folder.
         */
        owner,
        /**
         * The member can both view and edit the shared folder.
         */
        editor,
        /**
         * The member can only view the shared folder.
         */
        viewer,
        /**
         * An unknown access type.
         */
        other;  // *catch_all

        static final JsonWriter<AccessType> _writer = new JsonWriter<AccessType>()
        {
            public void write(AccessType x, JsonGenerator g)
             throws IOException
            {
                switch (x) {
                    case owner:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("owner");
                        g.writeEndObject();
                        break;
                    case editor:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("editor");
                        g.writeEndObject();
                        break;
                    case viewer:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("viewer");
                        g.writeEndObject();
                        break;
                    case other:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("other");
                        g.writeEndObject();
                        break;
                }
            }
        };

        public static final JsonReader<AccessType> _reader = new JsonReader<AccessType>()
        {
            public final AccessType read(JsonParser parser)
                throws IOException, JsonReadException
            {
                return JsonReader.readEnum(parser, _values, other);
            }
        };
        private static final java.util.HashMap<String,AccessType> _values;
        static {
            _values = new java.util.HashMap<String,AccessType>();
            _values.put("owner", owner);
            _values.put("editor", editor);
            _values.put("viewer", viewer);
            _values.put("other", other);
        }

        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static AccessType fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * Policy governing who can links be shared with.
     */
    public enum SharedLinkPolicy {
        // union SharedLinkPolicy
        /**
         * Links can be shared with anyone.
         */
        all,
        /**
         * Links can only be shared among members of the shared folder.
         */
        membersOnly,
        /**
         * An unknown shared link policy.
         */
        other;  // *catch_all

        static final JsonWriter<SharedLinkPolicy> _writer = new JsonWriter<SharedLinkPolicy>()
        {
            public void write(SharedLinkPolicy x, JsonGenerator g)
             throws IOException
            {
                switch (x) {
                    case all:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("all");
                        g.writeEndObject();
                        break;
                    case membersOnly:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("members_only");
                        g.writeEndObject();
                        break;
                    case other:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("other");
                        g.writeEndObject();
                        break;
                }
            }
        };

        public static final JsonReader<SharedLinkPolicy> _reader = new JsonReader<SharedLinkPolicy>()
        {
            public final SharedLinkPolicy read(JsonParser parser)
                throws IOException, JsonReadException
            {
                return JsonReader.readEnum(parser, _values, other);
            }
        };
        private static final java.util.HashMap<String,SharedLinkPolicy> _values;
        static {
            _values = new java.util.HashMap<String,SharedLinkPolicy>();
            _values.put("all", all);
            _values.put("members_only", membersOnly);
            _values.put("other", other);
        }

        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static SharedLinkPolicy fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * The information about a member of the shared folder.
     */
    public static class MembershipInfo {
        // struct MembershipInfo
        /**
         * This access type for this user member.
         */
        public final AccessType accessType;

        public MembershipInfo(AccessType accessType) {
            this.accessType = accessType;
            if (accessType == null) {
                throw new RuntimeException("Required value for 'accessType' is null");
            }
        }
        static final JsonWriter<MembershipInfo> _writer = new JsonWriter<MembershipInfo>()
        {
            public final void write(MembershipInfo x, JsonGenerator g)
             throws IOException
            {
                g.writeStartObject();
                MembershipInfo._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(MembershipInfo x, JsonGenerator g)
             throws IOException
            {
                g.writeFieldName("access_type");
                AccessType._writer.write(x.accessType, g);
            }
        };

        public static final JsonReader<MembershipInfo> _reader = new JsonReader<MembershipInfo>() {

            public final MembershipInfo read(JsonParser parser)
                throws IOException, JsonReadException
            {
                MembershipInfo result;
                JsonReader.expectObjectStart(parser);
                result = readFields(parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final MembershipInfo readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                AccessType accessType = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("access_type".equals(fieldName)) {
                        accessType = AccessType._reader
                            .readField(parser, "access_type", accessType);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (accessType == null) {
                    throw new JsonReadException("Required field \"access_type\" is missing.", parser.getTokenLocation());
                }
                return new MembershipInfo(accessType);
            }
        };

        public String toString() {
            return "MembershipInfo." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "MembershipInfo." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static MembershipInfo fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * The information about a user.
     */
    public static class UserInfo {
        // struct UserInfo
        /**
         * The account ID of the user.
         */
        public final String accountId;
        /**
         * The display name of the user.
         */
        public final String displayName;
        /**
         * If the user is in the same team as current user.
         */
        public final boolean sameTeam;
        /**
         * The member id of the user for the shared folder. This field will only
         * present if same_team field is true.
         */
        public final String memberId;

        public UserInfo(String accountId, String displayName, boolean sameTeam, String memberId) {
            this.accountId = accountId;
            if (accountId == null) {
                throw new RuntimeException("Required value for 'accountId' is null");
            }
            if (accountId.length() < 40) {
                throw new RuntimeException("String 'accountId' is shorter than 40");
            }
            if (accountId.length() > 40) {
                throw new RuntimeException("String 'accountId' is longer than 40");
            }
            this.displayName = displayName;
            if (displayName == null) {
                throw new RuntimeException("Required value for 'displayName' is null");
            }
            this.sameTeam = sameTeam;
            this.memberId = memberId;
        }
        static final JsonWriter<UserInfo> _writer = new JsonWriter<UserInfo>()
        {
            public final void write(UserInfo x, JsonGenerator g)
             throws IOException
            {
                g.writeStartObject();
                UserInfo._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(UserInfo x, JsonGenerator g)
             throws IOException
            {
                g.writeStringField("account_id", x.accountId);
                g.writeStringField("display_name", x.displayName);
                g.writeBooleanField("same_team", x.sameTeam);
                if (x.memberId != null) {
                    g.writeFieldName("member_id");
                    g.writeString(x.memberId);
                }
            }
        };

        public static final JsonReader<UserInfo> _reader = new JsonReader<UserInfo>() {

            public final UserInfo read(JsonParser parser)
                throws IOException, JsonReadException
            {
                UserInfo result;
                JsonReader.expectObjectStart(parser);
                result = readFields(parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final UserInfo readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                String accountId = null;
                String displayName = null;
                Boolean sameTeam = null;
                String memberId = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("account_id".equals(fieldName)) {
                        accountId = JsonReader.StringReader
                            .readField(parser, "account_id", accountId);
                    }
                    else if ("display_name".equals(fieldName)) {
                        displayName = JsonReader.StringReader
                            .readField(parser, "display_name", displayName);
                    }
                    else if ("same_team".equals(fieldName)) {
                        sameTeam = JsonReader.BooleanReader
                            .readField(parser, "same_team", sameTeam);
                    }
                    else if ("member_id".equals(fieldName)) {
                        memberId = JsonReader.StringReader
                            .readField(parser, "member_id", memberId);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (accountId == null) {
                    throw new JsonReadException("Required field \"account_id\" is missing.", parser.getTokenLocation());
                }
                if (displayName == null) {
                    throw new JsonReadException("Required field \"display_name\" is missing.", parser.getTokenLocation());
                }
                if (sameTeam == null) {
                    throw new JsonReadException("Required field \"same_team\" is missing.", parser.getTokenLocation());
                }
                return new UserInfo(accountId, displayName, sameTeam, memberId);
            }
        };

        public String toString() {
            return "UserInfo." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "UserInfo." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static UserInfo fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * The information about a user member of the shared folder.
     */
    public static class UserMembershipInfo extends MembershipInfo  {
        // struct UserMembershipInfo
        /**
         * The account information for the membership user.
         */
        public final UserInfo user;
        /**
         * If this membership is active. When the field is false, it means the
         * user has left the shared folder (but may still rejoin).
         */
        public final boolean active;

        public UserMembershipInfo(AccessType accessType, UserInfo user, boolean active) {
            super(accessType);
            this.user = user;
            if (user == null) {
                throw new RuntimeException("Required value for 'user' is null");
            }
            this.active = active;
        }
        static final JsonWriter<UserMembershipInfo> _writer = new JsonWriter<UserMembershipInfo>()
        {
            public final void write(UserMembershipInfo x, JsonGenerator g)
             throws IOException
            {
                g.writeStartObject();
                MembershipInfo._writer.writeFields(x, g);
                UserMembershipInfo._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(UserMembershipInfo x, JsonGenerator g)
             throws IOException
            {
                g.writeFieldName("user");
                UserInfo._writer.write(x.user, g);
                g.writeBooleanField("active", x.active);
            }
        };

        public static final JsonReader<UserMembershipInfo> _reader = new JsonReader<UserMembershipInfo>() {

            public final UserMembershipInfo read(JsonParser parser)
                throws IOException, JsonReadException
            {
                UserMembershipInfo result;
                JsonReader.expectObjectStart(parser);
                result = readFields(parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final UserMembershipInfo readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                AccessType accessType = null;
                UserInfo user = null;
                Boolean active = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("access_type".equals(fieldName)) {
                        accessType = AccessType._reader
                            .readField(parser, "access_type", accessType);
                    }
                    else if ("user".equals(fieldName)) {
                        user = UserInfo._reader
                            .readField(parser, "user", user);
                    }
                    else if ("active".equals(fieldName)) {
                        active = JsonReader.BooleanReader
                            .readField(parser, "active", active);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (accessType == null) {
                    throw new JsonReadException("Required field \"access_type\" is missing.", parser.getTokenLocation());
                }
                if (user == null) {
                    throw new JsonReadException("Required field \"user\" is missing.", parser.getTokenLocation());
                }
                if (active == null) {
                    throw new JsonReadException("Required field \"active\" is missing.", parser.getTokenLocation());
                }
                return new UserMembershipInfo(accessType, user, active);
            }
        };

        public String toString() {
            return "UserMembershipInfo." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "UserMembershipInfo." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static UserMembershipInfo fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * The information about a group. Group is a way to manage a list of users
     * who need same access permission to the shared folder.
     */
    public static class GroupInfo {
        // struct GroupInfo
        /**
         * The display name of the group.
         */
        public final String displayName;
        /**
         * The ID of the group.
         */
        public final String id;
        /**
         * The total number of member for this group.
         */
        public final long memberCount;
        /**
         * If all members of the group are in the same team as current user.
         */
        public final boolean sameTeam;

        public GroupInfo(String displayName, String id, long memberCount, boolean sameTeam) {
            this.displayName = displayName;
            if (displayName == null) {
                throw new RuntimeException("Required value for 'displayName' is null");
            }
            this.id = id;
            if (id == null) {
                throw new RuntimeException("Required value for 'id' is null");
            }
            this.memberCount = memberCount;
            this.sameTeam = sameTeam;
        }
        static final JsonWriter<GroupInfo> _writer = new JsonWriter<GroupInfo>()
        {
            public final void write(GroupInfo x, JsonGenerator g)
             throws IOException
            {
                g.writeStartObject();
                GroupInfo._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(GroupInfo x, JsonGenerator g)
             throws IOException
            {
                g.writeStringField("display_name", x.displayName);
                g.writeStringField("id", x.id);
                g.writeNumberField("member_count", x.memberCount);
                g.writeBooleanField("same_team", x.sameTeam);
            }
        };

        public static final JsonReader<GroupInfo> _reader = new JsonReader<GroupInfo>() {

            public final GroupInfo read(JsonParser parser)
                throws IOException, JsonReadException
            {
                GroupInfo result;
                JsonReader.expectObjectStart(parser);
                result = readFields(parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final GroupInfo readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                String displayName = null;
                String id = null;
                Long memberCount = null;
                Boolean sameTeam = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("display_name".equals(fieldName)) {
                        displayName = JsonReader.StringReader
                            .readField(parser, "display_name", displayName);
                    }
                    else if ("id".equals(fieldName)) {
                        id = JsonReader.StringReader
                            .readField(parser, "id", id);
                    }
                    else if ("member_count".equals(fieldName)) {
                        memberCount = JsonReader.Int64Reader
                            .readField(parser, "member_count", memberCount);
                    }
                    else if ("same_team".equals(fieldName)) {
                        sameTeam = JsonReader.BooleanReader
                            .readField(parser, "same_team", sameTeam);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (displayName == null) {
                    throw new JsonReadException("Required field \"display_name\" is missing.", parser.getTokenLocation());
                }
                if (id == null) {
                    throw new JsonReadException("Required field \"id\" is missing.", parser.getTokenLocation());
                }
                if (memberCount == null) {
                    throw new JsonReadException("Required field \"member_count\" is missing.", parser.getTokenLocation());
                }
                if (sameTeam == null) {
                    throw new JsonReadException("Required field \"same_team\" is missing.", parser.getTokenLocation());
                }
                return new GroupInfo(displayName, id, memberCount, sameTeam);
            }
        };

        public String toString() {
            return "GroupInfo." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "GroupInfo." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static GroupInfo fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * The information about a group member of the shared folder.
     */
    public static class GroupMembershipInfo extends MembershipInfo  {
        // struct GroupMembershipInfo
        /**
         * The information about the membership group.
         */
        public final GroupInfo group;

        public GroupMembershipInfo(AccessType accessType, GroupInfo group) {
            super(accessType);
            this.group = group;
            if (group == null) {
                throw new RuntimeException("Required value for 'group' is null");
            }
        }
        static final JsonWriter<GroupMembershipInfo> _writer = new JsonWriter<GroupMembershipInfo>()
        {
            public final void write(GroupMembershipInfo x, JsonGenerator g)
             throws IOException
            {
                g.writeStartObject();
                MembershipInfo._writer.writeFields(x, g);
                GroupMembershipInfo._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(GroupMembershipInfo x, JsonGenerator g)
             throws IOException
            {
                g.writeFieldName("group");
                GroupInfo._writer.write(x.group, g);
            }
        };

        public static final JsonReader<GroupMembershipInfo> _reader = new JsonReader<GroupMembershipInfo>() {

            public final GroupMembershipInfo read(JsonParser parser)
                throws IOException, JsonReadException
            {
                GroupMembershipInfo result;
                JsonReader.expectObjectStart(parser);
                result = readFields(parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final GroupMembershipInfo readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                AccessType accessType = null;
                GroupInfo group = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("access_type".equals(fieldName)) {
                        accessType = AccessType._reader
                            .readField(parser, "access_type", accessType);
                    }
                    else if ("group".equals(fieldName)) {
                        group = GroupInfo._reader
                            .readField(parser, "group", group);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (accessType == null) {
                    throw new JsonReadException("Required field \"access_type\" is missing.", parser.getTokenLocation());
                }
                if (group == null) {
                    throw new JsonReadException("Required field \"group\" is missing.", parser.getTokenLocation());
                }
                return new GroupMembershipInfo(accessType, group);
            }
        };

        public String toString() {
            return "GroupMembershipInfo." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "GroupMembershipInfo." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static GroupMembershipInfo fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * The base type for shared folder metadata.
     */
    public static class SharedFolderMetadata {
        // struct SharedFolderMetadata
        /**
         * The lower-cased full path of this shared folder.
         */
        public final String pathLower;
        /**
         * The name of the this shared folder.
         */
        public final String name;
        /**
         * The ID of the shared folder.
         */
        public final String id;
        /**
         * Who can access this shared folder.
         */
        public final AccessType accessType;
        /**
         * Who links can be shared with.
         */
        public final SharedLinkPolicy sharedLinkPolicy;

        public SharedFolderMetadata(String pathLower, String name, String id, AccessType accessType, SharedLinkPolicy sharedLinkPolicy) {
            this.pathLower = pathLower;
            if (pathLower == null) {
                throw new RuntimeException("Required value for 'pathLower' is null");
            }
            this.name = name;
            if (name == null) {
                throw new RuntimeException("Required value for 'name' is null");
            }
            this.id = id;
            if (id == null) {
                throw new RuntimeException("Required value for 'id' is null");
            }
            if (!java.util.regex.Pattern.matches("\\A[-_0-9a-zA-Z]+\\Z", id)) {
                throw new RuntimeException("String 'id' does not match pattern");
            }
            this.accessType = accessType;
            if (accessType == null) {
                throw new RuntimeException("Required value for 'accessType' is null");
            }
            this.sharedLinkPolicy = sharedLinkPolicy;
            if (sharedLinkPolicy == null) {
                throw new RuntimeException("Required value for 'sharedLinkPolicy' is null");
            }
        }
        public JsonWriter getWriter() {
            return SharedFolderMetadata._writer;
        }
        static final JsonWriter<SharedFolderMetadata> _writer = new JsonWriter<SharedFolderMetadata>()
        {
            public final void write(SharedFolderMetadata x, JsonGenerator g)
             throws IOException
            {
                JsonWriter w = x.getWriter();
                if (w != this) {
                    w.write(x, g);
                    return;
                }
                g.writeStartObject();
                SharedFolderMetadata._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(SharedFolderMetadata x, JsonGenerator g)
             throws IOException
            {
                g.writeStringField("path_lower", x.pathLower);
                g.writeStringField("name", x.name);
                g.writeStringField("id", x.id);
                g.writeFieldName("access_type");
                AccessType._writer.write(x.accessType, g);
                g.writeFieldName("shared_link_policy");
                SharedLinkPolicy._writer.write(x.sharedLinkPolicy, g);
            }
        };

        public static final JsonReader<SharedFolderMetadata> _reader = new JsonReader<SharedFolderMetadata>() {

            public final SharedFolderMetadata read(JsonParser parser)
                throws IOException, JsonReadException
            {
                SharedFolderMetadata result;
                JsonReader.expectObjectStart(parser);
                String[] tags = readTags(parser);
                result = readFromTags(tags, parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final SharedFolderMetadata readFromTags(String[] tags, JsonParser parser)
                throws IOException, JsonReadException
            {
                if (tags != null && tags.length > 0) {
                    if ("basic".equals(tags[0])) {
                        return BasicSharedFolderMetadata._reader.readFromTags(tags, parser);
                    }
                    if ("full".equals(tags[0])) {
                        return FullSharedFolderMetadata._reader.readFromTags(tags, parser);
                    }
                    // If no match, fall back to base class
                }
                return readFields(parser);
            }

            public final SharedFolderMetadata readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                String pathLower = null;
                String name = null;
                String id = null;
                AccessType accessType = null;
                SharedLinkPolicy sharedLinkPolicy = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("path_lower".equals(fieldName)) {
                        pathLower = JsonReader.StringReader
                            .readField(parser, "path_lower", pathLower);
                    }
                    else if ("name".equals(fieldName)) {
                        name = JsonReader.StringReader
                            .readField(parser, "name", name);
                    }
                    else if ("id".equals(fieldName)) {
                        id = JsonReader.StringReader
                            .readField(parser, "id", id);
                    }
                    else if ("access_type".equals(fieldName)) {
                        accessType = AccessType._reader
                            .readField(parser, "access_type", accessType);
                    }
                    else if ("shared_link_policy".equals(fieldName)) {
                        sharedLinkPolicy = SharedLinkPolicy._reader
                            .readField(parser, "shared_link_policy", sharedLinkPolicy);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (pathLower == null) {
                    throw new JsonReadException("Required field \"path_lower\" is missing.", parser.getTokenLocation());
                }
                if (name == null) {
                    throw new JsonReadException("Required field \"name\" is missing.", parser.getTokenLocation());
                }
                if (id == null) {
                    throw new JsonReadException("Required field \"id\" is missing.", parser.getTokenLocation());
                }
                if (accessType == null) {
                    throw new JsonReadException("Required field \"access_type\" is missing.", parser.getTokenLocation());
                }
                if (sharedLinkPolicy == null) {
                    throw new JsonReadException("Required field \"shared_link_policy\" is missing.", parser.getTokenLocation());
                }
                return new SharedFolderMetadata(pathLower, name, id, accessType, sharedLinkPolicy);
            }
        };

        public String toString() {
            return "SharedFolderMetadata." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "SharedFolderMetadata." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static SharedFolderMetadata fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * The metadata which includes basic information about the shared folder.
     */
    public static class BasicSharedFolderMetadata extends SharedFolderMetadata  {
        // struct BasicSharedFolderMetadata

        public BasicSharedFolderMetadata(String pathLower, String name, String id, AccessType accessType, SharedLinkPolicy sharedLinkPolicy) {
            super(pathLower, name, id, accessType, sharedLinkPolicy);
        }
        public JsonWriter getWriter() {
            return BasicSharedFolderMetadata._writer;
        }
        static final JsonWriter<BasicSharedFolderMetadata> _writer = new JsonWriter<BasicSharedFolderMetadata>()
        {
            public final void write(BasicSharedFolderMetadata x, JsonGenerator g)
             throws IOException
            {
                JsonWriter w = x.getWriter();
                if (w != this) {
                    w.write(x, g);
                    return;
                }
                g.writeStartObject();
                g.writeStringField(".tag", "basic");
                SharedFolderMetadata._writer.writeFields(x, g);
                BasicSharedFolderMetadata._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(BasicSharedFolderMetadata x, JsonGenerator g)
             throws IOException
            {
            }
        };

        public static final JsonReader<BasicSharedFolderMetadata> _reader = new JsonReader<BasicSharedFolderMetadata>() {

            public final BasicSharedFolderMetadata read(JsonParser parser)
                throws IOException, JsonReadException
            {
                BasicSharedFolderMetadata result;
                JsonReader.expectObjectStart(parser);
                String[] tags = readTags(parser);
                result = readFromTags(tags, parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final BasicSharedFolderMetadata readFromTags(String[] tags, JsonParser parser)
                throws IOException, JsonReadException
            {
                if (tags != null) {
                    assert tags.length >= 1;
                    assert "basic".equals(tags[0]);
                }
                return readFields(parser);
            }

            public final BasicSharedFolderMetadata readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                String pathLower = null;
                String name = null;
                String id = null;
                AccessType accessType = null;
                SharedLinkPolicy sharedLinkPolicy = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("path_lower".equals(fieldName)) {
                        pathLower = JsonReader.StringReader
                            .readField(parser, "path_lower", pathLower);
                    }
                    else if ("name".equals(fieldName)) {
                        name = JsonReader.StringReader
                            .readField(parser, "name", name);
                    }
                    else if ("id".equals(fieldName)) {
                        id = JsonReader.StringReader
                            .readField(parser, "id", id);
                    }
                    else if ("access_type".equals(fieldName)) {
                        accessType = AccessType._reader
                            .readField(parser, "access_type", accessType);
                    }
                    else if ("shared_link_policy".equals(fieldName)) {
                        sharedLinkPolicy = SharedLinkPolicy._reader
                            .readField(parser, "shared_link_policy", sharedLinkPolicy);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (pathLower == null) {
                    throw new JsonReadException("Required field \"path_lower\" is missing.", parser.getTokenLocation());
                }
                if (name == null) {
                    throw new JsonReadException("Required field \"name\" is missing.", parser.getTokenLocation());
                }
                if (id == null) {
                    throw new JsonReadException("Required field \"id\" is missing.", parser.getTokenLocation());
                }
                if (accessType == null) {
                    throw new JsonReadException("Required field \"access_type\" is missing.", parser.getTokenLocation());
                }
                if (sharedLinkPolicy == null) {
                    throw new JsonReadException("Required field \"shared_link_policy\" is missing.", parser.getTokenLocation());
                }
                return new BasicSharedFolderMetadata(pathLower, name, id, accessType, sharedLinkPolicy);
            }
        };

        public String toString() {
            return "BasicSharedFolderMetadata." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "BasicSharedFolderMetadata." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static BasicSharedFolderMetadata fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * The full metadata for the shared folder which includes user and group
     * membership.
     */
    public static class FullSharedFolderMetadata extends SharedFolderMetadata  {
        // struct FullSharedFolderMetadata
        /**
         * The list of user members of the shared folder.
         */
        public final java.util.ArrayList<UserMembershipInfo> membership;
        /**
         * The list of group members of the shared folder.
         */
        public final java.util.ArrayList<GroupMembershipInfo> groups;

        public FullSharedFolderMetadata(String pathLower, String name, String id, AccessType accessType, SharedLinkPolicy sharedLinkPolicy, java.util.ArrayList<UserMembershipInfo> membership, java.util.ArrayList<GroupMembershipInfo> groups) {
            super(pathLower, name, id, accessType, sharedLinkPolicy);
            this.membership = membership;
            if (membership == null) {
                throw new RuntimeException("Required value for 'membership' is null");
            }
            for (UserMembershipInfo x : membership) {
                if (x == null) {
                    throw new RuntimeException("An item in list 'membership' is null");
                }
            }
            this.groups = groups;
            if (groups == null) {
                throw new RuntimeException("Required value for 'groups' is null");
            }
            for (GroupMembershipInfo x : groups) {
                if (x == null) {
                    throw new RuntimeException("An item in list 'groups' is null");
                }
            }
        }
        public JsonWriter getWriter() {
            return FullSharedFolderMetadata._writer;
        }
        static final JsonWriter<FullSharedFolderMetadata> _writer = new JsonWriter<FullSharedFolderMetadata>()
        {
            public final void write(FullSharedFolderMetadata x, JsonGenerator g)
             throws IOException
            {
                JsonWriter w = x.getWriter();
                if (w != this) {
                    w.write(x, g);
                    return;
                }
                g.writeStartObject();
                g.writeStringField(".tag", "full");
                SharedFolderMetadata._writer.writeFields(x, g);
                FullSharedFolderMetadata._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(FullSharedFolderMetadata x, JsonGenerator g)
             throws IOException
            {
                g.writeFieldName("membership");
                g.writeStartArray();
                for (UserMembershipInfo item: x.membership) {
                    if (item != null) {
                        UserMembershipInfo._writer.write(item, g);
                    }
                }
                g.writeEndArray();
                g.writeFieldName("groups");
                g.writeStartArray();
                for (GroupMembershipInfo item: x.groups) {
                    if (item != null) {
                        GroupMembershipInfo._writer.write(item, g);
                    }
                }
                g.writeEndArray();
            }
        };

        public static final JsonReader<FullSharedFolderMetadata> _reader = new JsonReader<FullSharedFolderMetadata>() {

            public final FullSharedFolderMetadata read(JsonParser parser)
                throws IOException, JsonReadException
            {
                FullSharedFolderMetadata result;
                JsonReader.expectObjectStart(parser);
                String[] tags = readTags(parser);
                result = readFromTags(tags, parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final FullSharedFolderMetadata readFromTags(String[] tags, JsonParser parser)
                throws IOException, JsonReadException
            {
                if (tags != null) {
                    assert tags.length >= 1;
                    assert "full".equals(tags[0]);
                }
                return readFields(parser);
            }

            public final FullSharedFolderMetadata readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                String pathLower = null;
                String name = null;
                String id = null;
                AccessType accessType = null;
                SharedLinkPolicy sharedLinkPolicy = null;
                java.util.ArrayList<UserMembershipInfo> membership = null;
                java.util.ArrayList<GroupMembershipInfo> groups = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("path_lower".equals(fieldName)) {
                        pathLower = JsonReader.StringReader
                            .readField(parser, "path_lower", pathLower);
                    }
                    else if ("name".equals(fieldName)) {
                        name = JsonReader.StringReader
                            .readField(parser, "name", name);
                    }
                    else if ("id".equals(fieldName)) {
                        id = JsonReader.StringReader
                            .readField(parser, "id", id);
                    }
                    else if ("access_type".equals(fieldName)) {
                        accessType = AccessType._reader
                            .readField(parser, "access_type", accessType);
                    }
                    else if ("shared_link_policy".equals(fieldName)) {
                        sharedLinkPolicy = SharedLinkPolicy._reader
                            .readField(parser, "shared_link_policy", sharedLinkPolicy);
                    }
                    else if ("membership".equals(fieldName)) {
                        membership = JsonArrayReader.mk(UserMembershipInfo._reader)
                            .readField(parser, "membership", membership);
                    }
                    else if ("groups".equals(fieldName)) {
                        groups = JsonArrayReader.mk(GroupMembershipInfo._reader)
                            .readField(parser, "groups", groups);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (pathLower == null) {
                    throw new JsonReadException("Required field \"path_lower\" is missing.", parser.getTokenLocation());
                }
                if (name == null) {
                    throw new JsonReadException("Required field \"name\" is missing.", parser.getTokenLocation());
                }
                if (id == null) {
                    throw new JsonReadException("Required field \"id\" is missing.", parser.getTokenLocation());
                }
                if (accessType == null) {
                    throw new JsonReadException("Required field \"access_type\" is missing.", parser.getTokenLocation());
                }
                if (sharedLinkPolicy == null) {
                    throw new JsonReadException("Required field \"shared_link_policy\" is missing.", parser.getTokenLocation());
                }
                if (membership == null) {
                    throw new JsonReadException("Required field \"membership\" is missing.", parser.getTokenLocation());
                }
                if (groups == null) {
                    throw new JsonReadException("Required field \"groups\" is missing.", parser.getTokenLocation());
                }
                return new FullSharedFolderMetadata(pathLower, name, id, accessType, sharedLinkPolicy, membership, groups);
            }
        };

        public String toString() {
            return "FullSharedFolderMetadata." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "FullSharedFolderMetadata." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static FullSharedFolderMetadata fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * There is an error accessing the shared folder.
     */
    public enum SharedFolderAccessError {
        // union SharedFolderAccessError
        /**
         * This shared folder ID is invalid.
         */
        invalidId,
        /**
         * The user is not a member of the shared folder thus cannot access it.
         */
        notMember,
        /**
         * The user has left the shared folder already thus is no longer able to
         * access it.
         */
        hasLeft,
        /**
         * The user needs to be the owner to access the shared folder.
         */
        requireOwner,
        /**
         * The folder is a root folder and cannot be shared.
         */
        isRoot,
        /**
         * The folder is a team shared folder and the user cannot access it.
         */
        isTeamSharedFolder,
        /**
         * The folder is an app folder and cannot be shared.
         */
        isAppFolder;

        static final JsonWriter<SharedFolderAccessError> _writer = new JsonWriter<SharedFolderAccessError>()
        {
            public void write(SharedFolderAccessError x, JsonGenerator g)
             throws IOException
            {
                switch (x) {
                    case invalidId:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("invalid_id");
                        g.writeEndObject();
                        break;
                    case notMember:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("not_member");
                        g.writeEndObject();
                        break;
                    case hasLeft:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("has_left");
                        g.writeEndObject();
                        break;
                    case requireOwner:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("require_owner");
                        g.writeEndObject();
                        break;
                    case isRoot:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("is_root");
                        g.writeEndObject();
                        break;
                    case isTeamSharedFolder:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("is_team_shared_folder");
                        g.writeEndObject();
                        break;
                    case isAppFolder:
                        g.writeStartObject();
                        g.writeFieldName(".tag");
                        g.writeString("is_app_folder");
                        g.writeEndObject();
                        break;
                }
            }
        };

        public static final JsonReader<SharedFolderAccessError> _reader = new JsonReader<SharedFolderAccessError>()
        {
            public final SharedFolderAccessError read(JsonParser parser)
                throws IOException, JsonReadException
            {
                return JsonReader.readEnum(parser, _values, null);
            }
        };
        private static final java.util.HashMap<String,SharedFolderAccessError> _values;
        static {
            _values = new java.util.HashMap<String,SharedFolderAccessError>();
            _values.put("invalid_id", invalidId);
            _values.put("not_member", notMember);
            _values.put("has_left", hasLeft);
            _values.put("require_owner", requireOwner);
            _values.put("is_root", isRoot);
            _values.put("is_team_shared_folder", isTeamSharedFolder);
            _values.put("is_app_folder", isAppFolder);
        }

        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static SharedFolderAccessError fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * Arguments for {@link #getSharedFolder}.
     */
    public static class GetSharedFolderArgs {
        // struct GetSharedFolderArgs
        /**
         * The ID for the shared folder.
         */
        public final String id;
        /**
         * If include user and group membership information in the response.
         */
        public final boolean includeMembership;

        public GetSharedFolderArgs(String id, Boolean includeMembership) {
            this.id = id;
            if (id == null) {
                throw new RuntimeException("Required value for 'id' is null");
            }
            if (!java.util.regex.Pattern.matches("\\A[-_0-9a-zA-Z]+\\Z", id)) {
                throw new RuntimeException("String 'id' does not match pattern");
            }
            if (includeMembership != null) {
                this.includeMembership = includeMembership.booleanValue();
            }
            else {
                this.includeMembership = true;
            }
        }
        static final JsonWriter<GetSharedFolderArgs> _writer = new JsonWriter<GetSharedFolderArgs>()
        {
            public final void write(GetSharedFolderArgs x, JsonGenerator g)
             throws IOException
            {
                g.writeStartObject();
                GetSharedFolderArgs._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(GetSharedFolderArgs x, JsonGenerator g)
             throws IOException
            {
                g.writeStringField("id", x.id);
                g.writeBooleanField("include_membership", x.includeMembership);
            }
        };

        public static final JsonReader<GetSharedFolderArgs> _reader = new JsonReader<GetSharedFolderArgs>() {

            public final GetSharedFolderArgs read(JsonParser parser)
                throws IOException, JsonReadException
            {
                GetSharedFolderArgs result;
                JsonReader.expectObjectStart(parser);
                result = readFields(parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final GetSharedFolderArgs readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                String id = null;
                Boolean includeMembership = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("id".equals(fieldName)) {
                        id = JsonReader.StringReader
                            .readField(parser, "id", id);
                    }
                    else if ("include_membership".equals(fieldName)) {
                        includeMembership = JsonReader.BooleanReader
                            .readField(parser, "include_membership", includeMembership);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (id == null) {
                    throw new JsonReadException("Required field \"id\" is missing.", parser.getTokenLocation());
                }
                return new GetSharedFolderArgs(id, includeMembership);
            }
        };

        public String toString() {
            return "GetSharedFolderArgs." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "GetSharedFolderArgs." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static GetSharedFolderArgs fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * Arguments for {@link #listSharedFolders}.
     */
    public static class ListSharedFoldersArgs {
        // struct ListSharedFoldersArgs
        /**
         * If include user and group membership information in the response.
         */
        public final boolean includeMembership;

        public ListSharedFoldersArgs(Boolean includeMembership) {
            if (includeMembership != null) {
                this.includeMembership = includeMembership.booleanValue();
            }
            else {
                this.includeMembership = false;
            }
        }
        static final JsonWriter<ListSharedFoldersArgs> _writer = new JsonWriter<ListSharedFoldersArgs>()
        {
            public final void write(ListSharedFoldersArgs x, JsonGenerator g)
             throws IOException
            {
                g.writeStartObject();
                ListSharedFoldersArgs._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(ListSharedFoldersArgs x, JsonGenerator g)
             throws IOException
            {
                g.writeBooleanField("include_membership", x.includeMembership);
            }
        };

        public static final JsonReader<ListSharedFoldersArgs> _reader = new JsonReader<ListSharedFoldersArgs>() {

            public final ListSharedFoldersArgs read(JsonParser parser)
                throws IOException, JsonReadException
            {
                ListSharedFoldersArgs result;
                JsonReader.expectObjectStart(parser);
                result = readFields(parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final ListSharedFoldersArgs readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                Boolean includeMembership = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("include_membership".equals(fieldName)) {
                        includeMembership = JsonReader.BooleanReader
                            .readField(parser, "include_membership", includeMembership);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                return new ListSharedFoldersArgs(includeMembership);
            }
        };

        public String toString() {
            return "ListSharedFoldersArgs." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "ListSharedFoldersArgs." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static ListSharedFoldersArgs fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * Result for {@link #listSharedFolders}.
     */
    public static class ListSharedFoldersResult {
        // struct ListSharedFoldersResult
        /**
         * List of all shared folders the authenticated user has access to.
         */
        public final java.util.ArrayList<SharedFolderMetadata> entries;

        public ListSharedFoldersResult(java.util.ArrayList<SharedFolderMetadata> entries) {
            this.entries = entries;
            if (entries == null) {
                throw new RuntimeException("Required value for 'entries' is null");
            }
            for (SharedFolderMetadata x : entries) {
                if (x == null) {
                    throw new RuntimeException("An item in list 'entries' is null");
                }
            }
        }
        static final JsonWriter<ListSharedFoldersResult> _writer = new JsonWriter<ListSharedFoldersResult>()
        {
            public final void write(ListSharedFoldersResult x, JsonGenerator g)
             throws IOException
            {
                g.writeStartObject();
                ListSharedFoldersResult._writer.writeFields(x, g);
                g.writeEndObject();
            }
            public final void writeFields(ListSharedFoldersResult x, JsonGenerator g)
             throws IOException
            {
                g.writeFieldName("entries");
                g.writeStartArray();
                for (SharedFolderMetadata item: x.entries) {
                    if (item != null) {
                        SharedFolderMetadata._writer.write(item, g);
                    }
                }
                g.writeEndArray();
            }
        };

        public static final JsonReader<ListSharedFoldersResult> _reader = new JsonReader<ListSharedFoldersResult>() {

            public final ListSharedFoldersResult read(JsonParser parser)
                throws IOException, JsonReadException
            {
                ListSharedFoldersResult result;
                JsonReader.expectObjectStart(parser);
                result = readFields(parser);
                JsonReader.expectObjectEnd(parser);
                return result;
            }

            public final ListSharedFoldersResult readFields(JsonParser parser)
                throws IOException, JsonReadException
            {
                java.util.ArrayList<SharedFolderMetadata> entries = null;
                while (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String fieldName = parser.getCurrentName();
                    parser.nextToken();
                    if ("entries".equals(fieldName)) {
                        entries = JsonArrayReader.mk(SharedFolderMetadata._reader)
                            .readField(parser, "entries", entries);
                    }
                    else { JsonReader.skipValue(parser); }
                }
                if (entries == null) {
                    throw new JsonReadException("Required field \"entries\" is missing.", parser.getTokenLocation());
                }
                return new ListSharedFoldersResult(entries);
            }
        };

        public String toString() {
            return "ListSharedFoldersResult." + _writer.writeToString(this, false);
        }
        public String toStringMultiline() {
            return "ListSharedFoldersResult." + _writer.writeToString(this, true);
        }
        public String toJson(Boolean longForm) {
            return _writer.writeToString(this, longForm);
        }
        public static ListSharedFoldersResult fromJson(String s)
            throws JsonReadException
        {
            return _reader.readFully(s);
        }
    }

    /**
     * Exception thrown by {@link #getSharedLinks}.
     */
    public static class GetSharedLinksException extends DbxApiException {
        /**
         * The error reported by getSharedLinks.
         */
        public final GetSharedLinksError errorValue;

        public GetSharedLinksException(GetSharedLinksError errorValue) {
            super("Exception in get_shared_links: " + errorValue);
            this.errorValue = errorValue;
        }
    }
    /**
     * Returns a list of {@link LinkMetadata} objects for this user, including
     * collection links. If no path is given or the path is empty, returns a
     * list of all shared links for the current user, including collection
     * links. If a non-empty path is given, returns a list of all shared links
     * that allow access to the given path.  Collection links are never returned
     * in this case. Note that the url field in the response is never the
     * shortened URL. This API is not supported for App Folder and filetypes
     * apps.
     */
    private GetSharedLinksResult getSharedLinks(GetSharedLinksArg arg)
            throws GetSharedLinksException, DbxException
    {
        try {
            return DbxRawClientV2.rpcStyle(client.getRequestConfig(),
                                           client.getAccessToken(),
                                           client.getHost().api,
                                           "2-beta-2/sharing/get_shared_links",
                                           arg,
                                           GetSharedLinksArg._writer,
                                           GetSharedLinksResult._reader,
                                           GetSharedLinksError._reader);
        }
        catch (DbxRequestUtil.ErrorWrapper ew) {
            throw new GetSharedLinksException((GetSharedLinksError) (ew.errValue));
        }
    }
    /**
     * Returns a list of {@link LinkMetadata} objects for this user, including
     * collection links. If no path is given or the path is empty, returns a
     * list of all shared links for the current user, including collection
     * links. If a non-empty path is given, returns a list of all shared links
     * that allow access to the given path.  Collection links are never returned
     * in this case. Note that the url field in the response is never the
     * shortened URL. This API is not supported for App Folder and filetypes
     * apps.
     */
    public GetSharedLinksResult getSharedLinks()
          throws GetSharedLinksException, DbxException
    {
        GetSharedLinksArg arg = new GetSharedLinksArg(null);
        return getSharedLinks(arg);
    }
    /**
     * Returns a list of {@link LinkMetadata} objects for this user, including
     * collection links. If no path is given or the path is empty, returns a
     * list of all shared links for the current user, including collection
     * links. If a non-empty path is given, returns a list of all shared links
     * that allow access to the given path.  Collection links are never returned
     * in this case. Note that the url field in the response is never the
     * shortened URL. This API is not supported for App Folder and filetypes
     * apps.
     */
    public GetSharedLinksResult getSharedLinks(String path)
          throws GetSharedLinksException, DbxException
    {
        GetSharedLinksArg arg = new GetSharedLinksArg(path);
        return getSharedLinks(arg);
    }

    /**
     * Exception thrown by {@link #createSharedLink}.
     */
    public static class CreateSharedLinkException extends DbxApiException {
        /**
         * The error reported by createSharedLink.
         */
        public final CreateSharedLinkError errorValue;

        public CreateSharedLinkException(CreateSharedLinkError errorValue) {
            super("Exception in create_shared_link: " + errorValue);
            this.errorValue = errorValue;
        }
    }
    /**
     * Create a shared link. If a shared link already exists for the given path,
     * that link is returned. Note that in the returned {@link
     * PathLinkMetadata}, the url field is the shortened URL if the short_url
     * argument is set to {@literal true}. This API is not supported for App
     * Folder and filetypes apps.
     */
    private PathLinkMetadata createSharedLink(CreateSharedLinkArg arg)
            throws CreateSharedLinkException, DbxException
    {
        try {
            return DbxRawClientV2.rpcStyle(client.getRequestConfig(),
                                           client.getAccessToken(),
                                           client.getHost().api,
                                           "2-beta-2/sharing/create_shared_link",
                                           arg,
                                           CreateSharedLinkArg._writer,
                                           PathLinkMetadata._reader,
                                           CreateSharedLinkError._reader);
        }
        catch (DbxRequestUtil.ErrorWrapper ew) {
            throw new CreateSharedLinkException((CreateSharedLinkError) (ew.errValue));
        }
    }
    /**
     * Create a shared link. If a shared link already exists for the given path,
     * that link is returned. Note that in the returned {@link
     * PathLinkMetadata}, the url field is the shortened URL if the short_url
     * argument is set to {@literal true}. This API is not supported for App
     * Folder and filetypes apps.
     */
    public PathLinkMetadata createSharedLink(String path)
          throws CreateSharedLinkException, DbxException
    {
        CreateSharedLinkArg arg = new CreateSharedLinkArg(path, null, null);
        return createSharedLink(arg);
    }
    /**
     * The builder object returned by {@link #CreateSharedLinkBuilder}
     */
    public final class CreateSharedLinkBuilder
    {
        private String path;
        private Boolean shortUrl;
        private PendingUploadMode pendingUpload;
        private CreateSharedLinkBuilder(String path)
        {
            this.path = path;
        }
        public CreateSharedLinkBuilder shortUrl(boolean shortUrl)
        {
            this.shortUrl = shortUrl;
            return this;
        }
        public CreateSharedLinkBuilder pendingUpload(PendingUploadMode pendingUpload)
        {
            this.pendingUpload = pendingUpload;
            return this;
        }
        public PathLinkMetadata start() throws CreateSharedLinkException, DbxException
        {
            CreateSharedLinkArg arg = new CreateSharedLinkArg(path, shortUrl, pendingUpload);
            return Sharing.this.createSharedLink(arg);
        }
    }
    /**
     * Create a shared link. If a shared link already exists for the given path,
     * that link is returned. Note that in the returned {@link
     * PathLinkMetadata}, the url field is the shortened URL if the short_url
     * argument is set to {@literal true}. This API is not supported for App
     * Folder and filetypes apps.
     */
    public CreateSharedLinkBuilder createSharedLinkBuilder(String path)
    {
        return new CreateSharedLinkBuilder(path);
    }

    /**
     * Exception thrown by {@link #getSharedFolder}.
     */
    public static class GetSharedFolderException extends DbxApiException {
        /**
         * The error reported by getSharedFolder.
         */
        public final SharedFolderAccessError errorValue;

        public GetSharedFolderException(SharedFolderAccessError errorValue) {
            super("Exception in get_shared_folder: " + errorValue);
            this.errorValue = errorValue;
        }
    }
    /**
     * Gets shared folder by its folder ID.
     */
    private SharedFolderMetadata getSharedFolder(GetSharedFolderArgs arg)
            throws GetSharedFolderException, DbxException
    {
        try {
            return DbxRawClientV2.rpcStyle(client.getRequestConfig(),
                                           client.getAccessToken(),
                                           client.getHost().api,
                                           "2-beta-2/sharing/get_shared_folder",
                                           arg,
                                           GetSharedFolderArgs._writer,
                                           SharedFolderMetadata._reader,
                                           SharedFolderAccessError._reader);
        }
        catch (DbxRequestUtil.ErrorWrapper ew) {
            throw new GetSharedFolderException((SharedFolderAccessError) (ew.errValue));
        }
    }
    /**
     * Gets shared folder by its folder ID.
     */
    public SharedFolderMetadata getSharedFolder(String id)
          throws GetSharedFolderException, DbxException
    {
        GetSharedFolderArgs arg = new GetSharedFolderArgs(id, null);
        return getSharedFolder(arg);
    }
    /**
     * Gets shared folder by its folder ID.
     */
    public SharedFolderMetadata getSharedFolder(String id, boolean includeMembership)
          throws GetSharedFolderException, DbxException
    {
        GetSharedFolderArgs arg = new GetSharedFolderArgs(id, includeMembership);
        return getSharedFolder(arg);
    }

    /**
     * Exception thrown by {@link #listSharedFolders}.
     */
    public static class ListSharedFoldersException extends DbxApiException {
        public ListSharedFoldersException() {
            super("Exception in list_shared_folders");
        }
    }
    /**
     * Return the list of all shared folders the authenticated user has access
     * to.
     */
    private ListSharedFoldersResult listSharedFolders(ListSharedFoldersArgs arg)
            throws ListSharedFoldersException, DbxException
    {
        try {
            return DbxRawClientV2.rpcStyle(client.getRequestConfig(),
                                           client.getAccessToken(),
                                           client.getHost().api,
                                           "2-beta-2/sharing/list_shared_folders",
                                           arg,
                                           ListSharedFoldersArgs._writer,
                                           ListSharedFoldersResult._reader,
                                           JsonReader.VoidReader);
        }
        catch (DbxRequestUtil.ErrorWrapper ew) {
            throw new ListSharedFoldersException();
        }
    }
    /**
     * Return the list of all shared folders the authenticated user has access
     * to.
     */
    public ListSharedFoldersResult listSharedFolders()
          throws ListSharedFoldersException, DbxException
    {
        ListSharedFoldersArgs arg = new ListSharedFoldersArgs(null);
        return listSharedFolders(arg);
    }
    /**
     * Return the list of all shared folders the authenticated user has access
     * to.
     */
    public ListSharedFoldersResult listSharedFolders(boolean includeMembership)
          throws ListSharedFoldersException, DbxException
    {
        ListSharedFoldersArgs arg = new ListSharedFoldersArgs(includeMembership);
        return listSharedFolders(arg);
    }
}
