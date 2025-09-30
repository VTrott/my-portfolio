export interface Experience {
  id?: number;
  company: string;
  position: string;
  description: string;
  location?: string;
  startDate?: string;
  endDate?: string;
  isCurrent?: boolean;
  companyUrl?: string;
  companyLogoUrl?: string;
}
