import { render } from "@testing-library/react";
import { QueryClient, QueryClientProvider } from "react-query";
import React from "react";

export const renderQueryClient = (element: any) => {
  return render(
    <QueryClientProvider client={new QueryClient()}>
      {element}
    </QueryClientProvider>
  );
};
