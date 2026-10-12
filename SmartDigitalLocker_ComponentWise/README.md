# Smart Digital Locker — Java Swing Desktop App

A component-wise Java Swing desktop project for Windows. It uses a professional charcoal, teal and white theme. No violet accents, no emoji UI, no `run.bat`.

## Requirements
- JDK 21 (JDK 17+ should work)
- VS Code
- Extension Pack for Java in VS Code

## Project structure
```text
SmartDigitalLocker_ComponentWise/
  src/
    com/
      locker/
        Main.java
        Theme.java
        Document.java
        StorageManager.java
        SidebarPanel.java
        DocumentCard.java
        AddDocumentDialog.java
        DashboardFrame.java
```

## Run in VS Code
1. Extract this ZIP.
2. In VS Code, select **File > Open Folder** and choose the extracted project folder.
3. Install **Extension Pack for Java** if it is not already installed.
4. Open `src/com/locker/Main.java`.
5. Click **Run** above `main()`.

No batch file is included or needed.

## Features
- Desktop dashboard with charcoal/teal/white design
- Separate Java files for UI, theme, model and storage
- Add copies of PDF, JPG, JPEG, PNG, DOC and DOCX files
- Categories and search
- Open or delete a stored copy
- Open the local storage folder
- Metadata and files remain on this PC

## Storage
`%USERPROFILE%\SmartDigitalLocker\`
- `documents\` stores copies of added files.
- `documents.db` stores local metadata.

## Security note
This is basic local storage, not an encrypted vault. Do not store sensitive identity documents until encryption and access control are implemented. The app does not upload documents to a server. Deleting an entry removes only the copy stored inside the locker; the original source file remains unchanged.
