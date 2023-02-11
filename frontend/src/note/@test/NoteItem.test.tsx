import React from "react";
import { screen } from "@testing-library/react";
// import { NoteItem } from "../NoteItem";
// import { Note } from "../type/NoteType";
// import { getNotes } from "../api/noteApi";

// jest.mock("../api/noteApi");
// const apiMock = getNotes as jest.MockedFunction<typeof getNotes>;
//
// const noteItemData: Note = {
//   id: 1,
//   title: "Test Note",
//   noteBody: "Test Note Body",
//   pinned: false,
//   createdDate: new Date().toISOString(),
//   lastModifiedDate: new Date().toISOString(),
// };

describe("NoteItem", () => {
  it("should render Create Note item", async () => {
    //     apiMock.mockResolvedValue([noteItemData]);
    //     const note = renderQueryClient(<NoteItem note={noteItemData} />);
    //     expect(note).toBeTruthy();
    expect(await screen.findByText("Test Note")).toBeVisible();
    expect(await screen.findByText("Test Note Body")).toBeVisible();
  });
});
