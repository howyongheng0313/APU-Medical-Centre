# APU Medical Centre - Manager Comment View

## Overview
This project implements a comprehensive comment management system for APU Medical Centre managers. The system allows managers to view all doctors and their associated customer comments in an intuitive, card-based interface.

## Features

### 1. Doctor Cards Overview
- **Scrollable Layout**: Displays all doctors in a responsive card layout
- **Comment Count**: Each card shows the total number of comments received by the doctor
- **Doctor Information**: Displays doctor name, specialization, and department
- **Interactive Design**: Cards have hover effects and are clickable

### 2. Detailed Comment View
- **Individual Doctor View**: Click on any doctor card to see detailed comments
- **Comment Details**: Shows customer name, rating, date, and full comment text
- **Average Rating**: Displays the average rating for each doctor
- **Responsive Layout**: Scrollable comment list with clean formatting

### 3. Statistics Dashboard
- **Total Doctors**: Shows the total number of doctors in the system
- **Total Comments**: Displays the overall comment count across all doctors
- **Real-time Updates**: Statistics update automatically when data changes

## Project Structure

```
src/amc/
├── model/
│   ├── Doctor.java          # Doctor entity with comment management
│   └── Comment.java         # Comment entity with customer feedback
├── controller/manager/
│   └── CommentService.java  # Service layer for comment management
├── view/
│   ├── viewCommentPanel.java           # Main comment overview panel
│   ├── DoctorCard.java                 # Individual doctor card component
│   ├── DoctorCommentDetailPanel.java   # Detailed comment view
│   ├── TestCommentView.java            # Test application
│   └── uiUtils.java                    # UI utility functions
```

## Key Components

### Doctor Model
- Manages doctor information (ID, name, specialization, department)
- Maintains a list of associated comments
- Provides methods to calculate comment count and average ratings

### Comment Model
- Stores customer feedback (customer name, content, rating, timestamp)
- Links comments to specific doctors
- Includes rating system (1-5 stars)

### CommentService
- Singleton service for managing doctor and comment data
- Provides sample data for demonstration
- Offers filtering and search capabilities
- Calculates statistics and ratings

### DoctorCard Component
- Displays doctor information in an attractive card format
- Shows comment count with visual indicators
- Implements hover effects and click interactions
- Opens detailed view when clicked

### DoctorCommentDetailPanel
- Comprehensive view of all comments for a specific doctor
- Displays customer feedback with ratings and timestamps
- Shows doctor statistics and average ratings
- Scrollable interface for large comment lists

## Usage

### Running the Application
1. Compile the project using your preferred Java IDE
2. Run `TestCommentView.java` to launch the demonstration
3. The main window will display all doctor cards
4. Click on any doctor card to view detailed comments

### Sample Data
The system includes sample data for 6 doctors across different departments:
- Cardiology: Dr. John Smith (3 comments)
- Neurology: Dr. Sarah Johnson (2 comments)
- Orthopedics: Dr. Michael Brown (4 comments)
- Pediatrics: Dr. Emily Davis (1 comment)
- Dermatology: Dr. David Wilson (2 comments)
- Oncology: Dr. Lisa Anderson (3 comments)

## Technical Details

### UI Design
- **Modern Interface**: Clean, professional design with consistent styling
- **Responsive Layout**: Adapts to different window sizes
- **Color Scheme**: Professional medical theme with teal accents
- **Typography**: Segoe UI font family for readability

### Data Management
- **In-Memory Storage**: Sample data stored in memory for demonstration
- **Singleton Pattern**: CommentService ensures single data source
- **Object-Oriented Design**: Clean separation of concerns

### User Experience
- **Intuitive Navigation**: Clear visual hierarchy and navigation
- **Interactive Elements**: Hover effects and click feedback
- **Loading States**: Smooth transitions and responsive interactions
- **Error Handling**: Graceful handling of edge cases

## Future Enhancements

### Potential Features
1. **Database Integration**: Connect to real database for persistent storage
2. **Search and Filter**: Add search functionality for doctors and comments
3. **Export Capabilities**: Export comment reports to PDF/Excel
4. **Comment Management**: Allow managers to respond to or moderate comments
5. **Analytics Dashboard**: Advanced statistics and trend analysis
6. **User Authentication**: Secure access control for managers

### Technical Improvements
1. **Data Validation**: Input validation and error handling
2. **Performance Optimization**: Pagination for large datasets
3. **Caching**: Implement caching for better performance
4. **Logging**: Add comprehensive logging for debugging
5. **Unit Testing**: Comprehensive test coverage

## Requirements
- Java 8 or higher
- Swing/AWT for GUI components
- No external dependencies required

## Installation
1. Clone the repository
2. Open in your preferred Java IDE (NetBeans, Eclipse, IntelliJ)
3. Build the project
4. Run `TestCommentView.java` to start the application

## Contributing
This is a demonstration project for APU Medical Centre. For production use, additional security, validation, and database integration would be required. 