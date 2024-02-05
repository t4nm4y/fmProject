import React from 'react';
export declare const LanguageContext: React.Context<{
    language: string;
    translations: any;
}>;
declare const LocalisationProvider: ({ children, language }: {
    children: any;
    language: any;
}) => import("@emotion/react/jsx-runtime").JSX.Element;
export { LocalisationProvider };
