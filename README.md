**AI powered Email Assistant**
I have used Spring Boot in backend and also created a web page to manually use the project.
- Technologies Used: Spring Boot, Spring AI, React, Google's Gemini API, RESTful APIs, Browser Extension Development.
- Designed and implemented the backend architecture using Spring Boot to handle email processing and AI integration.
- Integrated Google's Gemini API to facilitate AI-driven email response generation.
- Implemented error handling and user feedback mechanisms.

I developed a Chrome extension that integrates with Gmail to provide an AI-powered reply generation feature. This extension allows users to generate professional email replies with a single click, leveraging the capabilities of the Gemini AI model. The backend of this project is built using Spring Boot, which handles the API requests for generating email replies.
Key Features
1. AI Reply Button: The extension adds a custom "AI Reply" button to the Gmail compose toolbar.
2. Email Content Extraction: It extracts the content of the email thread to generate a contextually relevant reply.
3. Real-time Reply Generation: Upon clicking the "AI Reply" button, the extension sends the email content to the backend, which uses the Gemini AI model to generate a professional reply.
4. Seamless Integration: The generated reply is inserted directly into the compose box, allowing users to review and send it immediately.

Code Walkthrough
Content Script:
Button Creation: The createAIButton function creates a custom button with specific styles and attributes.
Email Content Extraction: The getEmailContent function uses CSS selectors to extract the email content from the Gmail interface.
Toolbar Detection: The findComposeToolbar function locates the compose toolbar using various CSS selectors.
Button Injection: The injectButton function adds the custom button to the compose toolbar and sets up the click event listener.
Mutation Observer: The observer watches for changes in the DOM to detect when the compose window is opened and injects the button accordingly.
