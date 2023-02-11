import * as React from 'react';
import * as ReactDOM from 'react-dom/client';
import './index.css';
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
import {QueryClient, QueryClientProvider} from 'react-query';
import App from "./App";
import {CssBaseline} from "@mui/material";

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
const queryClient = new QueryClient();



root.render(
    <QueryClientProvider client={queryClient} contextSharing={true}>
        <CssBaseline />
                <App />.
    </QueryClientProvider>,
);

